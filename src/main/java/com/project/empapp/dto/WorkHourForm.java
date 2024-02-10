package com.project.empapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.empapp.models.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkHourForm {

    @NotNull(message = "社員番号が無効です")
    @JsonProperty("emp_code")
    private Employee employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("work_day")
    private Date workDay;


    @NotNull(message = "Work Start Time is required")
    @JsonProperty("work_start")
    private String workStart;

    @NotNull(message = "Work Start Time is required")
    @JsonProperty("work_finish")
    private String workFinish;

}
