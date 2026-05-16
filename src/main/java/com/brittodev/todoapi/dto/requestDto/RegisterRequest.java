package com.brittodev.todoapi.dto.requestDto;


import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.scheduling.support.SimpleTriggerContext;

@Getter
@AllArgsConstructor
public class RegisterRequest {
    private String userName;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
}
