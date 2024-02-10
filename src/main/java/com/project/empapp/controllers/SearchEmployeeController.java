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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/employeeSearch")
public class SearchEmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("")
    public String showEmployeeSearchForm(Model model) {
        // 表示するフォームを返す
        model.addAttribute("searchEmployeeForm", new SearchEmployeeForm());
        return "employeeSearch";
    }

    @PostMapping("")
    public String resultSearchEmployeeByEmpAge(Model model, SearchEmployeeForm searchEmployeeForm){
        // フォームから受け取ったempAgeでemployeesテーブルを検索
        //List<Employee> employees = employeeService.findEmployeesByEmpAge(searchEmployeeForm.getEmpAge());
        // 検索結果をビューに渡す

        String condition = searchEmployeeForm.getCondition();
        String searchValue = searchEmployeeForm.getSearchValue();
        // 選択された条件でデータベースを検索
        List<Employee> employees = employeeService.findEmployeesByCondition(condition, searchValue);


        model.addAttribute("employees", employees);

        // 結果を表示するビューを返す
        return "employeeSearch";
    }

}
