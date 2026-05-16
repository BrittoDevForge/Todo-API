package com.brittodev.todoapi.dto.responseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
public class UserResponse {
    private String userName;
    private String email;
}
