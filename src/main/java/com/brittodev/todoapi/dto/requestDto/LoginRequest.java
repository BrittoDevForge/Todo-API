package com.brittodev.todoapi.dto.requestDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class LoginRequest {
    @Email
    private String email;
    @Size(min=8)
    private String password;
}
