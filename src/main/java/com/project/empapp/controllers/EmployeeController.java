package com.project.empapp.controllers;

import com.project.empapp.dto.EmployeeForm;
import com.project.empapp.models.Employee;
import com.project.empapp.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/employees")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees"; // Assuming "employees" is the name of your view file (e.g., employees.html)
    }

    @GetMapping("/employeeRegister")
    public  String createEmployee(Model model){
        model.addAttribute("employeeForm",new EmployeeForm());
        return "employeeRegister";
    }



    @PostMapping("/employeeForm")
    public String registerEmployee(@Valid @ModelAttribute("employeeForm") EmployeeForm employeeForm,
                                   BindingResult bindingResult, Model model){
        if (!employeeService.isEmpCodeUnique(employeeForm.getEmpCode())) {
            model.addAttribute("empCodeError", "社員番号が既に存在しています");
            return "employeeRegister";
        }
        if(bindingResult.hasErrors()){
            return "employeeRegister";
        }
        employeeService.registerEmployee(employeeForm);
        return "redirect:/employees";
    }


    @GetMapping("/edit/{empCode}")
    public String editEmployeeForm(@PathVariable("empCode") Long empCode, Model model ){
        model.addAttribute("employee", employeeService.getEmployeeById(empCode));
        return "editEmployee";

    }
    //update
    @PostMapping("/update/{empCode}")
    public String updateEmployee(@Valid @PathVariable("empCode") Long empCode,
                                 @ModelAttribute("employee") EmployeeForm employeeForm,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "editEmployee";
        }
        employeeService.updateEmployee(empCode, employeeForm);
        return "redirect:/employees";
    }


    //delete
    @GetMapping("/delete/{empCode}")
    public String deleteEmployee(@PathVariable("empCode") Long empCode){
        employeeService.deleteEmployee(empCode);
        return "redirect:/employees";
    }


}