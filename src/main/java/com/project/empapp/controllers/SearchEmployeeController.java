package com.project.empapp.controllers;



import com.project.empapp.dto.SearchEmployeeForm;
import com.project.empapp.models.Employee;
import com.project.empapp.services.EmployeeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Data
public class SearchEmployeeController {

    private final EmployeeService employeeService;


    @GetMapping("/employeeSearch")
    public  String employeeSearch(Model model){
        model.addAttribute("searchEmployeeForm",new SearchEmployeeForm());
        return "employeeSearch";
    }

    @PostMapping("/employeeSearch")
    public String resultSearchEmployeeByAge(Model model,SearchEmployeeForm searchEmployeeForm){
        String condition = searchEmployeeForm.getCondition();
        String searchValue = searchEmployeeForm.getSearchValue();
        List<Employee> employees = employeeService.findEmployeeByCondition(condition,searchValue);
        model.addAttribute("employees",employees);
        return "employeeSearch";
    }
}
