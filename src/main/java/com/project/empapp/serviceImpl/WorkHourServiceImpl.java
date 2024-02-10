package com.project.empapp.serviceImpl;

import com.project.empapp.dto.SearchWorkHourForm;
import com.project.empapp.dto.WorkHourDTO;
import com.project.empapp.dto.WorkHourForm;
import com.project.empapp.models.Employee;
import com.project.empapp.models.WorkHour;
import com.project.empapp.repositories.EmployeeRepository;
import com.project.empapp.repositories.WorkHourRepository;
import com.project.empapp.services.WorkHourService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
@Service
public class WorkHourServiceImpl implements WorkHourService {

    private final WorkHourRepository workHourRepository;

    private final EmployeeRepository employeeRepository;

    @Override
    public void registerWork(WorkHourForm workHourForm) {
        // WorksFormからWorksに変換する
        WorkHour workHour  = new WorkHour();

        // WorksFormからEmployeeを取得
        Employee employee = workHourForm.getEmployee();
        // Employeeがnullでないか確認
        if(employee != null){
            // Employeeが既にデータベースに存在するか確認
            Optional<Employee> existingEmployee = employeeRepository.findById(employee.getEmpCode());
            if(existingEmployee.isPresent()){
                workHour.setEmployee(existingEmployee.get());
            }else{
                // データベースに存在しない場合は新しく保存
                employeeRepository.save(employee);
                workHour.setEmployee(employee);
            }
        }

        workHour.setWorkDay(workHourForm.getWorkDay());
        workHour.setWorkStart(workHourForm.getWorkStart());
        workHour.setWorkFinish(workHourForm.getWorkFinish());
        workHour.setBreakTime("1:00");
        workHour.setWorkTime(calculateWorkTime(workHourForm.getWorkStart(),workHourForm.getWorkFinish()));
        workHour.setOverTime(calculateOverTime(workHourForm.getWorkStart(),workHourForm.getWorkFinish()));

        workHourRepository.save(workHour);

    }

    @Override
    public List<WorkHourDTO> findByWorkDayAndEmployee_Department(Date workDay, String department) {
        return workHourRepository.findByWorkDayAndEmployee_Department(workDay, department);
    }

    @Override
    public List<WorkHourDTO> searchByCondition(SearchWorkHourForm searchForm) {
        String condition = searchForm.getCondition();
        String searchValue = searchForm.getSearchValue();

        switch (condition) {
            case "workDay":
                // workDayに基づいて検索する処理
                return workHourRepository.findByWorkDayAndEmployee_Department(LocalDate.parse(searchValue), "dummyDepartment");
            case "department":
                // departmentに基づいて検索する処理
                return workHourRepository.findByDepartment("dummyDepartment");
            default:
                // 他の条件に基づいて検索する処理
                return Collections.emptyList();
        }
    }


//    @Override
//    public List<WorkHourDTO> findWorkHoursByCondition(Date workDay, String searchValue, String condition) {
//        switch (condition){
//            case "workDay":
//                return workHourRepository.findByWorkDayAndEmployee_Department(workDay, searchValue);
//            case "department":
//                return workHourRepository.findByEmployee_Department(searchValue);
//            default :
//                return Collections.emptyList();
//        }
//
//    }


//    @Override
//    public List<WorkHourDTO> findWorkHoursByCondition(String condition, String searchValue) {
//        return switch (condition) {
//            case "workDay" -> {
//                Date workDay = convertToDate(searchValue);
//                yield workHourRepository.findByWorkDay(workDay);
//                // フォームからの入力を Date に変換
//            }
//            case "department" -> workHourRepository.findByDepartment(searchValue);
//            default -> Collections.emptyList();
//        };
//    }



    private Date convertToDate(String dateString) {
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            return  format.parse(dateString);
        }catch (ParseException e){
            throw new RuntimeException("変換できません", e);
        }
    }


    //労働時間計算
    private String calculateWorkTime(String startHour, String endHour) {
        // 休憩時間を差し引いた労働時間を計算
        int workTimeHours = calculateHoursBetween(startHour, endHour) - 1;
        return formatTime(workTimeHours, 0);
    }

    private int calculateHoursBetween(String startHour, String endHour) {
        // 時間の差を計算 08:30
        int startMinutes = Integer.parseInt(startHour.split(":")[0]) * 60 + Integer.parseInt(startHour.split(":")[1]);
        int endMinutes = Integer.parseInt(endHour.split(":")[0]) * 60 + Integer.parseInt(endHour.split(":")[1]);
        //総労働時間
        return (endMinutes - startMinutes) / 60;
    }
    //残業時間計算
    private String calculateOverTime(String startHour, String endHour) {

        String []startHourStr = startHour.split(":");
        String []endHourStr = endHour.split(":");

        String start_Hour = startHourStr[0];
        String start_Minute = startHourStr[1];

        String end_Hour = endHourStr[0];
        String end_Minute = endHourStr[1];

        // 文字列をLocalTimeに変換
        LocalTime startTime = LocalTime.of(Integer.parseInt(start_Hour), Integer.parseInt(start_Minute));
        int overtimeMinutes = getOvertimeMinutes(end_Hour, end_Minute, startTime);

        if (overtimeMinutes <= 0) {
            // 労働時間が休憩時間を含めて8時間以下の場合は残業なし
            return "00:00";
        }

        // 8時間を超えた場合に残業時間を計算
        int overTimeHours = overtimeMinutes / 60;
        int overTimeMinutes = overtimeMinutes % 60;

        return formatTime(overTimeHours, overTimeMinutes);
    }

    private static int getOvertimeMinutes(String end_Hour, String end_Minute, LocalTime startTime) {
        LocalTime endTime = LocalTime.of(Integer.parseInt(end_Hour), Integer.parseInt(end_Minute));

        // 通常労働時間と休憩時間を設定（8時間労働と1時間休憩を設定）
        int normalWorkingHours = 8;
        int breakTime = 60; // 休憩時間（分）

        // 始業から終業までの労働時間を計算
        int workingHours = endTime.getHour() - startTime.getHour();
        int workingMinutes = endTime.getMinute() - startTime.getMinute();
        int totalWorkingMinutes = workingHours * 60 + workingMinutes;

        // 休憩時間を差し引く
        int netWorkingMinutes = totalWorkingMinutes - breakTime;

        // 残業時間を計算
        return netWorkingMinutes - normalWorkingHours * 60;
    }


    private String formatTime(int hours, int minutes) {
        // hh:mm形式にフォーマットする
        return String.format("%02d:%02d", hours, minutes);
    }
}
