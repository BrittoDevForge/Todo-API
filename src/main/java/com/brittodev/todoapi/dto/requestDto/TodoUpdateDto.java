package com.brittodev.todoapi.dto.requestDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateDto {
    Long id;
    String title;
    String description;
    Boolean isCompleted;
}
