package com.project.empapp.serviceImpl;


import com.project.empapp.dto.LoginForm;
import com.project.empapp.models.Admin;
import com.project.empapp.repositories.AdminRepository;
import com.project.empapp.services.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor // goi all Constructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public boolean validateAdmin(LoginForm loginForm) {
        Admin admin = adminRepository.findByUsername(loginForm.getUsername());
        return admin != null && admin.getPassword().equals(loginForm.getPassword());
    }
}
