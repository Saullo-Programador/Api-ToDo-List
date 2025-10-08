package com.example.todo_list.infrastructure.entity;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

    private String message;
    private int code;
    private T data;

    public ApiResponse(String message, HttpStatus status, T data) {
        this.message = message;
        this.code = status.value();
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
