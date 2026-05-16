package com.brittodev.todoapi.service;


import com.brittodev.todoapi.dto.requestDto.LoginRequest;
import com.brittodev.todoapi.dto.requestDto.RegisterRequest;
import com.brittodev.todoapi.dto.responseDto.UserResponse;
import com.brittodev.todoapi.entity.User;
import com.brittodev.todoapi.exception.custom.ResourceAlreadyExistsException;
import com.brittodev.todoapi.exception.custom.ResourceNotFoundException;
import com.brittodev.todoapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("user is already exist");
        }

        User user = userRepository.save(
                User.builder()
                        .userName(request.getUserName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build()
        );

        return buildUserResponse(user);

    }

    public UserResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            throw new RuntimeException("invalid password");
        }
        return buildUserResponse(user);
    }


    // Response builder helper
    private UserResponse buildUserResponse(User user) {
        return UserResponse.builder()
                .userName(user.getUserName()).email(user.getEmail()).build();
    }
}
