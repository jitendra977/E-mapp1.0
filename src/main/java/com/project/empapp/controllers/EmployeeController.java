package com.project.empapp.controllers;

import com.project.empapp.dto.EmployeeForm;
import com.project.empapp.models.Employee;
import com.project.empapp.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Data
//@RequestMapping("/app/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    //get employees
    @GetMapping("/employees")
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employeeRegister")
    public String createEmployee(Model model) {
        model.addAttribute("employeeForm" , new EmployeeForm());
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

    //update
    @GetMapping("/employees/edit/{empCode}")
    public String editEmployeeForm(@Valid @PathVariable Long empCode, Model model ){
        model.addAttribute("employee", employeeService.getEmployeeById(empCode));
        return "editEmployee";

    }

    @PostMapping("/employees/{empCode}")
    public String updateEmployee(@Valid @PathVariable Long empCode,
                                 @ModelAttribute("employee") EmployeeForm employeeForm,
                                 BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            return "editEmployee";
        }
        employeeService.updateEmployee(empCode,employeeForm);
        return "redirect:/employees";
    }

    //delete
    @GetMapping("/employees/{empCode}")
    public String deleteEmployee(@PathVariable Long empCode){
        employeeService.deleteEmployee(empCode);
        return "redirect:/employees";
    }

}
