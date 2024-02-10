package com.project.empapp.models;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
public class Admin implements Serializable {
	@Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable= false)
    private int id;
    
    
    @NotBlank(message = "username is required")
    @Column(name= "username", nullable= false)
    private String username;
    
    @NotBlank(message = "password is required")
    @Column(name= "password", nullable= false)
    private String password;
}
