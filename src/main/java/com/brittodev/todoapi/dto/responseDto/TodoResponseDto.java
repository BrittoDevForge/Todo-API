package com.brittodev.todoapi.dto.responseDto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoResponseDto {
    private Long id;
    private String title;
    private String description;
    private Boolean isCompleted;
}
