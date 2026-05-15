package com.brittodev.todoapi.service;


import ch.qos.logback.core.joran.conditional.IfAction;
import com.brittodev.todoapi.dto.requestDto.TodoCreateDto;
import com.brittodev.todoapi.dto.requestDto.TodoUpdateDto;
import com.brittodev.todoapi.dto.responseDto.TodoResponseDto;
import com.brittodev.todoapi.entity.Todo;
import com.brittodev.todoapi.repository.TodoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public TodoResponseDto createTodo(TodoCreateDto dto) {
        return getResponseDto(todoRepository.save(createTodoFromDto(dto)));
    }

    // update the todo
    public TodoResponseDto updateTodo(TodoUpdateDto dto) {
        if(dto.getId() == null) {
            throw new RuntimeException("please provide the todo id");
        }
        Todo existing = todoRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("todo not found"));

        existing.setIsCompleted(dto.getIsCompleted());
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());

        return getResponseDto(todoRepository.save(existing));
    }

    // delete the todo by id
    public void deleteTodo(Long id) {
        if(id == null) {
            throw new RuntimeException("provide todo id");
        }
        if(!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo not found for id " + id);
        }
        todoRepository.deleteById(id);
    }

    // delete all todo
    public void deleteAllTodo() {
            todoRepository.deleteAll();
    }

    // get todo by id
    public TodoResponseDto getTodo(Long id) {
        if (id == null) {
            throw new RuntimeException("provide todo id");
        }
        Todo todo =  todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        return getResponseDto(todo);
    }

    // get All todo
    public List<TodoResponseDto> getAllTodo() {
        return getResponseDtos(todoRepository.findAll());
    }

    // pagination
    public Page<TodoResponseDto> getTodos(Pageable pageable) {
        return todoRepository.findAll(pageable)
                .map(this::getResponseDto);
    }

    // search
    public Page<TodoResponseDto> getByTitle(String title , Pageable pageable) {
        return todoRepository.findTodoByTitleContains(title,pageable)
                .map(this::getResponseDto);
    }
    public Page<TodoResponseDto> getByTitleAndCompletion(String title , Boolean isCompleted , Pageable pageable) {
        return todoRepository.findTodoByTitleContainsAndIsCompleted(title,isCompleted,pageable)
                .map(this::getResponseDto);
    }
    public Page<TodoResponseDto> getByCompletion(Boolean isCompleted , Pageable pageable) {
        return todoRepository.findTodoByIsCompleted(isCompleted,pageable)
                .map(this::getResponseDto);
    }




    // helper for Dto Creation
    public Todo createTodoFromDto(TodoCreateDto dto) {
        return Todo.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }



    public TodoResponseDto getResponseDto(Todo todo) {
        return TodoResponseDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .isCompleted(todo.getIsCompleted())
                .build();
    }

    public List<TodoResponseDto> getResponseDtos(List<Todo> todos) {
        return todos.stream()
                .map(this::getResponseDto)
                .toList();
    }

}
