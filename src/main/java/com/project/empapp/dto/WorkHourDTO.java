package com.project.empapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class WorkHourDTO {
    @JsonProperty("emp_name")
    private String empName;

    @JsonProperty("work_day")
    private Date workDay;

    @JsonProperty("work_start")
    private String workTime;

    @JsonProperty("work_finish")
    private String overTime;

    public WorkHourDTO(String empName, Date workDay, String workTime, String overTime) {
        this.empName = empName;
        this.workDay = workDay;
        this.workTime = workTime;
        this.overTime = overTime;
    }
}
