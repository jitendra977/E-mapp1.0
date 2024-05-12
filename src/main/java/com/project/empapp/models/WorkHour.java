package com.project.empapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "works")
public class WorkHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "emp_code", referencedColumnName = "emp_code",  nullable = false)
    @NotNull(message = "社員番号が無効です")
    private Employee employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "work_day", nullable = false)
    private Date workDay;


    @NotNull(message = "Work Start Time is required")
    @Column(name = "work_start", nullable = false)
    private String workStart;

    @NotNull(message = "Work Finish Time is required")
    @Column(name = "work_finish", nullable = false)
    private String workFinish;

    @Column(name = "work_time", nullable = false)
    private String workTime;

    @Column(name = "break_time", nullable = false)
    private String breakTime;

    @Column(name = "over_time" , nullable = false)
    private String overTime;


}
