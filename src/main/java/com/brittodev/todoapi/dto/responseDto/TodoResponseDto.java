package com.brittodev.todoapi.dto.responseDto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoResponseDto {
    Long id;
    String title;
    String description;
    Boolean isCompleted;
}
