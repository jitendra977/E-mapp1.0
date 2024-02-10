package com.project.empapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "emp_code", nullable = false)
    @NotNull(message = "Employee Code is required")
    private Long empCode;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "work_year",nullable = true)
    private Integer workYear;

    @Pattern(regexp = "^[a-zA-Z\\u4e00-\\u9fa5ァ-ヶー]+$", message = "名前は文字列だけ入力できます")
    @Column(name = "emp_name", nullable = false)
    private String empName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "emp_age",nullable = true)
    private Integer empAge;

    @Column(name = "last_date",nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date lastDate;

    @Pattern(regexp = "^[a-zA-Z\\u4e00-\\u9fa5ァ-ヶー]+$", message = "名前は文字列だけ入力できます")
    @Column(name = "department", nullable = false)
    private String department;


}
