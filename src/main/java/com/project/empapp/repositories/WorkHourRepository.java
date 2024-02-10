package com.project.empapp.repositories;

import com.project.empapp.dto.WorkHourDTO;
import com.project.empapp.dto.WorkHourForm;
import com.project.empapp.models.Employee;
import com.project.empapp.models.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WorkHourRepository extends JpaRepository<WorkHour, Long> {
    @Override
    List<WorkHour> findAll();

    List<WorkHourDTO> findByWorkDayAndEmployee_Department(LocalDate workDay, String department);

//    @Query("SELECT new com.project.empapp.dto.WorkHourDTO(e.empName, w.workDay, w.workTime, w.overTime) " +
//            "FROM WorkHour w " +
//            "JOIN Employee e ON w.employee.empCode = e.empCode " +
//            "WHERE e.department = :department")
    List<WorkHourDTO> findByEmployee_Department(String department);

    List<WorkHour> findByDepartment(Employee dep);

}
