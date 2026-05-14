package com.brittodev.todoapi.service;


import ch.qos.logback.core.joran.conditional.IfAction;
import com.brittodev.todoapi.entity.Todo;
import com.brittodev.todoapi.repository.TodoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    // creating the todo
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    // update the todo
    public Todo updateTodo(Todo todo) {
        Todo existing = todoRepository.findById(todo.getId()).orElseThrow(() -> new RuntimeException("todo not found"));

        existing.setIsCompleted(todo.getIsCompleted());
        existing.setTitle(todo.getTitle());
        existing.setDescription(todo.getDescription());

        return todoRepository.save(existing);
    }

    // delete the todo by id
    public void deleteTodo(Long id) {
        if(!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo not found for id " + id);
        }
        todoRepository.deleteById(id);
    }

    // delete all todo
    public void deleteAllTodo() {
        try {
            todoRepository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // get todo by id
    public Todo getTodo(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    // get All todo
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }


}
