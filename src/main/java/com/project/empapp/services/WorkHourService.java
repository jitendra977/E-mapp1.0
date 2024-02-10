package com.project.empapp.services;

import com.project.empapp.dto.SearchWorkHourForm;
import com.project.empapp.dto.WorkHourDTO;
import com.project.empapp.dto.WorkHourForm;
import com.project.empapp.models.WorkHour;

import java.util.Date;
import java.util.List;

public interface WorkHourService {
    void registerWork(WorkHourForm workHourForm);

    //List<WorkHourDTO> findWorkHoursByCondition(String condition, String searchValue);

    //List<WorkHourDTO> findWorkHoursByCondition(Date workDay, String searchValue, String condition);
    List<WorkHourDTO> findByWorkDayAndEmployee_Department(LocalDate workDay, String department);

    List<WorkHourDTO> searchByCondition(SearchWorkHourForm searchForm);

}
