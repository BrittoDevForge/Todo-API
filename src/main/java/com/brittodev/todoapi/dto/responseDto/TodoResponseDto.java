package com.brittodev.todoapi.dto.responseDto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class TodoResponseDto {
    private Long id;
    private String title;
    private String description;
    private Boolean isCompleted;
}
