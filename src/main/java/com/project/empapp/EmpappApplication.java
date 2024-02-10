package com.project.empapp;

import com.project.empapp.models.Employee;
import com.project.empapp.repositories.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Data
public class EmpappApplication   {

	public static void main(String[] args) {
		SpringApplication.run(EmpappApplication.class, args);
	}



}

