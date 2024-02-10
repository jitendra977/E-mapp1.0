package com.project.empapp.controllers;

import com.project.empapp.dto.WorkHourForm;
import com.project.empapp.models.WorkHour;
import com.project.empapp.services.WorkHourService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@Data
public class WorkHourController {
    private final WorkHourService workHourService;

    @GetMapping("/workhourRegister")
    public String showWorkHourForm(Model model){
        model.addAttribute("workhourRegister", new WorkHourForm());
        return "workhourRegister";
    }

    @PostMapping("/workhourRegister")
    public String createWorkHour(@Valid @ModelAttribute("workhourRegister") WorkHourForm workHourForm,
                                 BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "workhourRegister";
        }

        workHourService.registerWork(workHourForm);
        return "redirect:/employees";

    }


}
