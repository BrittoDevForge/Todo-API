package com.brittodev.todoapi.controller;


import com.brittodev.todoapi.entity.Todo;
import com.brittodev.todoapi.service.TodoService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    // create the todo
    @PostMapping("/create")
    ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.createTodo(todo));
    }

    // update the todo
    @PutMapping("/update")
    ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.updateTodo(todo));
    }

    // delete todo by id
    @DeleteMapping("/delete")
    ResponseEntity<String> deleteTodo(@RequestParam Long id) {
        try {
            todoService.deleteTodo(id);
            return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    // delete All todo
    @DeleteMapping("/deleteAll")
    ResponseEntity<String> deleteAllTodo() {
        try {
            todoService.deleteAllTodo();
            return new ResponseEntity<>("deleted all todo",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    // get todo
    @GetMapping("/get")
    ResponseEntity<Todo> getTodo(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getTodo(id));
    }

    // getAllTodo
    @GetMapping("/getAll")
    ResponseEntity<List<Todo>> getAllTodo() {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(todoService.getAllTodo());
    }

}
