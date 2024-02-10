package com.project.empapp.repositories;

import com.project.empapp.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmpCode(Long empCode);

    //List<Employee> findByEmpCode(Long empCode);
    List<Employee> findByEmpAge(int empAge);

    List<Employee> findByWorkYear(int workYear);

    List<Employee> findByEmpName(String empName);

    List<Employee> findByDepartment(String department);

    List<Employee> findByEmpNameAndLastDateIsNotNull(String empName);


}
