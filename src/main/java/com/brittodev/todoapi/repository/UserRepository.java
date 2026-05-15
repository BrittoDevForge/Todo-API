package com.brittodev.todoapi.repository;

import com.brittodev.todoapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
