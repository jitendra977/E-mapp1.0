package com.project.empapp.dto;

import lombok.Data;

@Data
public class SearchEmployeeForm {

    private String condition;
    private String searchValue;

    private Integer empAge;

    private Integer workYear;

    private  String department;

    private  String empName;

}
