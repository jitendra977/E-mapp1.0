package com.project.empapp.serviceImpl;

import com.project.empapp.dto.EmployeeForm;
import com.project.empapp.models.Employee;
import com.project.empapp.repositories.EmployeeRepository;
import com.project.empapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean isEmpCodeUnique(Long empCode) {

        return employeeRepository.findByEmpCode(empCode) == null;
    }

    @Override
    public void registerEmployee(EmployeeForm employeeForm) {
        Employee employee = new Employee();
        employee.setEmpCode(employeeForm.getEmpCode());
        employee.setEmpName(employeeForm.getEmpName());
        employee.setStartDate(employeeForm.getStartDate());
        employee.setBirthday(employeeForm.getBirthday());
        employee.setDepartment(employeeForm.getDepartment());
        employee.setLastDate(employeeForm.getLastDate());

        // 年齢の計算
        if(employeeForm.getBirthday() != null){
            LocalDate birthDate = employeeForm.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            int emp_age = Period.between(birthDate, currentDate).getYears();
            employee.setEmpAge(emp_age);
        }
        // 勤務年数の計算
        if(employeeForm.getStartDate() != null){
            LocalDate startDate = employeeForm.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            int work_year = Period.between(startDate, currentDate).getYears();
            employee.setWorkYear(work_year);
        }
        // データベースに保存
        employeeRepository.save(employee);
    }


    @Override
    public Employee getEmployeeById(Long empCode) {
        return employeeRepository.findByEmpCode(empCode);
    }
    // update
    @Override
    public void updateEmployee(Long empCode, EmployeeForm employeeForm) {
        Employee existingEmployee = employeeRepository.findByEmpCode(empCode);

        existingEmployee.setEmpCode(employeeForm.getEmpCode());
        existingEmployee.setEmpName(employeeForm.getEmpName());
        existingEmployee.setStartDate(employeeForm.getStartDate());
        existingEmployee.setBirthday(employeeForm.getBirthday());
        existingEmployee.setDepartment(employeeForm.getDepartment());
        existingEmployee.setLastDate(employeeForm.getLastDate());

        // 年齢の計算
        if(employeeForm.getBirthday() != null){
            LocalDate birthDate = employeeForm.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            int emp_age = Period.between(birthDate, currentDate).getYears();
            existingEmployee.setEmpAge(emp_age);
        }
        // 勤務年数の計算
        if(employeeForm.getStartDate() != null){
            LocalDate startDate = employeeForm.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            int work_year = Period.between(startDate, currentDate).getYears();
            existingEmployee.setWorkYear(work_year);
        }
        // データベースに保存
        employeeRepository.save(existingEmployee);
    }
    @Override
    public void deleteEmployee(Long empCode) {
        employeeRepository.deleteById(empCode);
    }

    @Override
    public List<Employee> findEmployeeByCondition(String condition, String searchValue) {
        System.out.println(condition);
        System.out.println(searchValue);
        return switch (condition){
            case "empAge" -> employeeRepository.findByEmpAge(Integer.parseInt(searchValue));
            case "workYear" -> employeeRepository.findByWorkYear(Integer.parseInt(searchValue));
            case "empName" -> employeeRepository.findByEmpNameAndLastDateIsNotNull(searchValue);
            case "department" -> employeeRepository.findByDepartment(searchValue);
            default -> Collections.emptyList();


        };

    }
}
