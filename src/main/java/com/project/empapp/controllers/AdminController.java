package com.project.empapp.controllers;

import com.project.empapp.dto.LoginForm;
import com.project.empapp.services.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
//@RequestMapping("/login")
public class AdminController {

    private final   AdminService adminService;

    @GetMapping("/login") /* http: */
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        if (adminService.validateAdmin(loginForm)) {
            // ユーザーが存在し、パスワードが一致する場合
            return "redirect:/homepage";
        } else {
            // ユーザーが存在しないか、パスワードが一致しない場合
            model.addAttribute("error", "ユーザー名かパスワードが無効");
            return "login";
        }
    }
    @GetMapping("/homepage")
    public String showHomepage() {
        return "homepage";
    }

}
