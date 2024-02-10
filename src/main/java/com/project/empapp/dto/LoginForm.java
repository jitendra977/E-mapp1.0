package com.project.empapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    @NotEmpty(message = "ユーザー名が必須")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;
}
