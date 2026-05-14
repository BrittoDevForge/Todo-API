package com.brittodev.todoapi.repository;

import com.brittodev.todoapi.entity.Todo;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {


    Page<Todo> findTodoByTitleContains(String title,Pageable pageable);

    Page<Todo> findTodoByIsCompleted(Boolean isCompleted, Pageable pageable);

    Page<Todo> findTodoByTitleContainsAndIsCompleted(String title, Boolean isCompleted, Pageable pageable);



}
