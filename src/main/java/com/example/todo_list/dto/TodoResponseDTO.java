package com.example.todo_list.dto;

import java.util.Date;

public class TodoResponseDTO {
    private Long id;
    private String todoTitle;
    private String todoDescription;
    private boolean isComplete;
    private Date todoDate;
    private Date createDate;
    private Date updateDate;

    public TodoResponseDTO(Long id, String todoTitle, String todoDescription, boolean isComplete, Date todoDate, Date createDate, Date updateDate) {
        this.id = id;
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.isComplete = isComplete;
        this.todoDate = todoDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}

