package com.project.empapp.services;

import com.project.empapp.dto.LoginForm;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    //boolean validateAdmin(String username, String password);
    boolean validateAdmin(LoginForm loginForm);
}
