package com.project.empapp.services;


import com.project.empapp.dto.WorkHourForm;
import com.project.empapp.models.WorkHour;

import java.util.List;

public interface WorkHourService {
     List<WorkHour> getAllWorkHour();
     void registerWork(WorkHourForm workHourForm);


}
