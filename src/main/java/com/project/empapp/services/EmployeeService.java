package com.project.empapp.services;

import com.project.empapp.dto.EmployeeForm;
import com.project.empapp.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
    List<Employee> getAllEmployees();

    void registerEmployee(EmployeeForm employeeForm);

    boolean isEmpCodeUnique(Long empCode);

    Employee getEmployeeById(Long empCode);

    void updateEmployee(Long empCode, EmployeeForm employeeForm);

    void deleteEmployee(Long empCode);

    //Search empAge
    //List<Employee> findEmployeesByEmpAge(int empAge);

    List<Employee> findEmployeesByCondition(String condition, String searchValue);

}
