package com.brittodev.todoapi.exception;


import com.brittodev.todoapi.dto.responseDto.error.ErrorResponse;
import com.brittodev.todoapi.exception.custom.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException exception ,
            HttpServletRequest servletRequest
    ) {
        log.atInfo().log("exception on resource not found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("NOT FOUND")
                        .message(exception.getMessage())
                        .path(servletRequest.getRequestURI())
                        .build());
    }
}
