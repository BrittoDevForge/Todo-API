package com.brittodev.todoapi.dto.requestDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
public class TodoCreateDto {
    @NotBlank(message = "title should not be empty")
    private String title;
    @NotNull(message = "description not to be null , can be empty")
    private String description;
}
