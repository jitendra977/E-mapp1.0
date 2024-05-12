package com.project.empapp.services;

import com.project.empapp.dto.EmployeeForm;
import com.project.empapp.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    boolean isEmpCodeUnique(Long empCode);
    void registerEmployee(EmployeeForm employeeForm);
    Employee getEmployeeById(Long empCode);
    void updateEmployee(Long empCode, EmployeeForm employeeForm);
    void deleteEmployee(Long empCode);
    List<Employee> findEmployeeByCondition(String condition,String searchValue);
}
