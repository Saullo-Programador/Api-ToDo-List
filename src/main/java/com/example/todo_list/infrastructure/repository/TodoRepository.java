package com.example.todo_list.infrastructure.repository;

import com.example.todo_list.infrastructure.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository  extends JpaRepository<Todo, Long> {
}
