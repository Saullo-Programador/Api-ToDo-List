package com.example.todo_list.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class TodoRequestDTO {

    @NotBlank
    @Size(min = 5, message = "O título deve ter pelo menos 5 caracteres")
    private String todoTitle;

    @NotBlank
    @Size(min = 5, message = "A descrição deve ter pelo menos 5 caracteres")
    private String todoDescription;

    private boolean isComplete;
    private Date todoDate;

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }
}
