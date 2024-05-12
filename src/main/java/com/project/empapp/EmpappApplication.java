package com.project.empapp;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Data
public class EmpappApplication   {

	public static void main(String[] args) {
		SpringApplication.run(EmpappApplication.class, args);
	}



}

