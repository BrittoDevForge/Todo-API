package com.brittodev.todoapi.dto.requestDto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
public class TodoUpdateDto {
    private Long id;
    @NotBlank(message = "title not to be null")
    private String title;
    @NotNull(message = "description not to be null , can be empty")
    private String description;

    private Boolean isCompleted;
}
