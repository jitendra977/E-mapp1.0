package com.project.empapp.dto;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {

    @NotNull(message = "Employee Code is required")
    private Long empCode;

    @NotNull(message = "Start Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotBlank(message = "Employee Name is required")
    @Pattern(regexp = "^[a-zA-Z\\u4e00-\\u9fa5ァ-ヶー]+$", message = "名前は文字列だけ入力できます")
    private String empName;

    @NotNull(message = "Birthday is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastDate;

    @NotBlank(message = "Department is required")
    @Pattern(regexp = "^[a-zA-Z\\u4e00-\\u9fa5ァ-ヶー]+$", message = "名前は文字列だけ入力できます")
    private String department;

}
