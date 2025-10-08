package com.example.todo_list.service;

import com.example.todo_list.dto.TodoRequestDTO;
import com.example.todo_list.dto.TodoResponseDTO;
import com.example.todo_list.infrastructure.entity.Todo;
import com.example.todo_list.infrastructure.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponseDTO> list() {
        return todoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TodoResponseDTO getById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
        return toResponseDTO(todo);
    }

    public TodoResponseDTO create(TodoRequestDTO dto) {
        Todo todo = new Todo();
        todo.setTodoTitle(dto.getTodoTitle());
        todo.setTodoDescription(dto.getTodoDescription());
        todo.setTodoDate(dto.getTodoDate());
        todo.setComplete(dto.isComplete());
        todoRepository.save(todo);
        return toResponseDTO(todo);
    }

    public TodoResponseDTO update(Long id, TodoRequestDTO dto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));

        todo.setTodoTitle(dto.getTodoTitle());
        todo.setTodoDescription(dto.getTodoDescription());
        todo.setTodoDate(dto.getTodoDate());
        todo.setComplete(dto.isComplete());

        todoRepository.save(todo);
        return toResponseDTO(todo);
    }

    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado");
        }
        todoRepository.deleteById(id);
    }

    public long count() {
        return todoRepository.count();
    }

    private TodoResponseDTO toResponseDTO(Todo todo) {
        return new TodoResponseDTO(
                todo.getId(),
                todo.getTodoTitle(),
                todo.getTodoDescription(),
                todo.isComplete(),
                todo.getTodoDate(),
                todo.getCreateDate(),
                todo.getUpdateDate()
        );
    }
}