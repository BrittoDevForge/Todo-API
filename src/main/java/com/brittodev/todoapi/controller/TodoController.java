package com.brittodev.todoapi.controller;


import com.brittodev.todoapi.dto.requestDto.TodoCreateDto;
import com.brittodev.todoapi.dto.requestDto.TodoUpdateDto;
import com.brittodev.todoapi.dto.responseDto.TodoResponseDto;
import com.brittodev.todoapi.entity.Todo;
import com.brittodev.todoapi.service.TodoService;
import jakarta.persistence.GeneratedValue;
import org.apache.tomcat.util.http.parser.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.createTodo(dto));
    }

    // update the todo
    @PutMapping("/update")
    ResponseEntity<TodoResponseDto> updateTodo(@RequestBody TodoUpdateDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.updateTodo(dto));
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
    ResponseEntity<TodoResponseDto> getTodo(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getTodo(id));
    }

    // getAllTodo
    @GetMapping("/getAll")
    ResponseEntity<List<TodoResponseDto>> getAllTodo() {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(todoService.getAllTodo());
    }


    @GetMapping("/page")
    ResponseEntity<Page<TodoResponseDto>> getWithPage(
            @RequestParam(defaultValue = "0")
            int page ,
            @RequestParam(defaultValue = "5")
            int size ,
            @RequestParam(defaultValue = "id")
            String sortBy ,
            @RequestParam(defaultValue = "asc")
            String  direction
    ) {
        Sort sort = (direction.equals("desc")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return new ResponseEntity<>(todoService.getTodos(pageable),HttpStatus.OK);
    }

    // search
    @GetMapping("/search")
    ResponseEntity<Page<TodoResponseDto>> search(
            @RequestParam(defaultValue = "0")
            int page ,
            @RequestParam(defaultValue = "5")
            int size ,
            @RequestParam(defaultValue = "id")
            String sortBy ,
            @RequestParam(defaultValue = "asc")
            String direction ,
            @RequestParam(required = false)
            String title ,
            @RequestParam(required = false)
            Boolean isCompleted
    ) {
        Sort sort = (direction.equals("desc")) ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        if(title != null && isCompleted != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(todoService.getByTitleAndCompletion(title, isCompleted,pageable));
        } else if (title != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(todoService.getByTitle(title,pageable));
        } else if (isCompleted!=null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(todoService.getByCompletion(isCompleted,pageable));
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(todoService.getTodos(pageable));
        }
    }

}
