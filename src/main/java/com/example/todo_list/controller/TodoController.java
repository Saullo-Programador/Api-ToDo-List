package com.example.todo_list.controller;


import com.example.todo_list.dto.TodoRequestDTO;
import com.example.todo_list.dto.TodoResponseDTO;
import com.example.todo_list.infrastructure.entity.ApiResponse;
import com.example.todo_list.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos/")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // ✅ Criar novo TODO
    @PostMapping
    public ResponseEntity<ApiResponse<TodoResponseDTO>> create(@Valid @RequestBody TodoRequestDTO request) {
        TodoResponseDTO todo = todoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Item adicionado à lista", HttpStatus.CREATED, todo));
    }

    // ✅ Listar todos
    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getAll() {
        return ResponseEntity.ok(todoService.list());
    }

    // ✅ Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponseDTO>> getById(@PathVariable Long id) {
        TodoResponseDTO todo = todoService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>("Item encontrado", HttpStatus.OK, todo));
    }

    // ✅ Atualizar TODO
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody TodoRequestDTO request) {
        TodoResponseDTO updated = todoService.update(id, request);
        return ResponseEntity.ok(new ApiResponse<>("Item atualizado", HttpStatus.OK, updated));
    }

    // ✅ Deletar TODO
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>("Item deletado", HttpStatus.OK, null));
    }

    // ✅ Contagem
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> count() {
        return ResponseEntity.ok(new ApiResponse<>("Contagem total de itens", HttpStatus.OK, todoService.count()));
    }
}