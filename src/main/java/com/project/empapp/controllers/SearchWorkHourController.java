package com.project.empapp.controllers;


import com.project.empapp.dto.SearchWorkHourForm;
import com.project.empapp.models.WorkHour;
import com.project.empapp.services.EmployeeService;
import com.project.empapp.services.WorkHourService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Data
@RequiredArgsConstructor
@Controller
public class SearchWorkHourController {
    @Autowired
    private WorkHourService workHourService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/workhourSearch")
    public  String workhourSearch(Model model){
        model.addAttribute("searchWorkHourForm",new SearchWorkHourForm());
        return "workhourSearch";
    }


    @GetMapping("/allWorkHours")
    public String listWorkHour(Model model) {
        List<WorkHour> workingHour = workHourService.getAllWorkHour();
        model.addAttribute("workingHour", workingHour);
        return "workhourSearch"; // Assuming "workhours" is the name of your view file for displaying work hours
    }

}
