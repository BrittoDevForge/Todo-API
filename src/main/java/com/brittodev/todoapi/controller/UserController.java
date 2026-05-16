package com.brittodev.todoapi.controller;


import com.brittodev.todoapi.dto.requestDto.LoginRequest;
import com.brittodev.todoapi.dto.requestDto.RegisterRequest;
import com.brittodev.todoapi.dto.responseDto.UserResponse;
import com.brittodev.todoapi.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        log.atInfo().log("register called");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(request));
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.loginUser(request));
    }
}
