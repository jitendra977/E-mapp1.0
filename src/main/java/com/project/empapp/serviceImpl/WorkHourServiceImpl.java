package com.project.empapp.serviceImpl;


import com.project.empapp.dto.WorkHourForm;
import com.project.empapp.models.Employee;
import com.project.empapp.models.WorkHour;
import com.project.empapp.repositories.EmployeeRepository;
import com.project.empapp.repositories.WorkHourRepository;
import com.project.empapp.services.WorkHourService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkHourServiceImpl implements WorkHourService {
    @Autowired
    private  WorkHourRepository workHourRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<WorkHour> getAllWorkHour() {
               return workHourRepository.findAll();

    }

    @Override
    public void registerWork(WorkHourForm workHourForm) {
        WorkHour workHour = new WorkHour();

        Employee employee = workHourForm.getEmployee();
        if (employee != null) {
            Optional<Employee> existingEmployee = employeeRepository.findById(employee.getEmpCode());
            existingEmployee.ifPresentOrElse(workHour::setEmployee, () -> {
                employeeRepository.save(employee);
                workHour.setEmployee(employee);
            });
        }

        workHour.setWorkDay(workHourForm.getWorkDay());
        workHour.setWorkStart(workHourForm.getWorkStart());
        workHour.setWorkFinish(workHourForm.getWorkFinish());
        workHour.setBreakTime("1:00");
        workHour.setWorkTime(calculateWorkTime(workHourForm.getWorkStart(), workHourForm.getWorkFinish()));
        workHour.setOverTime(calculateOverTime(workHourForm.getWorkStart(), workHourForm.getWorkFinish()));

        workHourRepository.save(workHour);
    }



    private String calculateOverTime(String startHour, String endHour) {
        LocalTime startTime = LocalTime.parse(startHour);
        LocalTime endTime = LocalTime.parse(endHour);

        // Calculate the total working minutes
        long totalMinutesWorked = Duration.between(startTime, endTime).toMinutes();

        // Calculate the overtime
        long normalWorkingMinutes = 8 * 60; // 8 hours
        long breakTimeMinutes = 60; // 1 hour break
        long overtimeMinutes = totalMinutesWorked - normalWorkingMinutes - breakTimeMinutes;

        // Ensure overtime is not negative
        overtimeMinutes = Math.max(overtimeMinutes, 0);

        // Convert overtime minutes to hours and minutes
        long overtimeHours = overtimeMinutes / 60;
        long overtimeRemainingMinutes = overtimeMinutes % 60;

        // Return the formatted overtime string
        return String.format("%02d:%02d", overtimeHours, overtimeRemainingMinutes);
    }

    private String calculateWorkTime(String workStart, String workFinish) {
        LocalTime startTime = LocalTime.parse(workStart);
        LocalTime endTime = LocalTime.parse(workFinish);

        // Calculate the total working minutes
        long totalMinutesWorked = Duration.between(startTime, endTime).toMinutes();

        // Convert total minutes worked to hours and minutes
        long hoursWorked = totalMinutesWorked / 60;
        long remainingMinutes = totalMinutesWorked % 60;

        // Return the formatted work time string
        return String.format("%02d:%02d", hoursWorked, remainingMinutes);
    }
}
