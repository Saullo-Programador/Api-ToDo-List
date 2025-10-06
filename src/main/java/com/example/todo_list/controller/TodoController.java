package com.example.todo_list.controller;


import com.example.todo_list.infrastructure.entity.ResponseToDoList;
import com.example.todo_list.infrastructure.entity.Todo;
import com.example.todo_list.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(TodoController.BASE_URL)
public class TodoController {
    public static final String BASE_URL = "/api/v1/todo";
    private static final Logger logger = Logger.getLogger(TodoController.class.getName());

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public List<Todo> getListToDos(){
        return todoService.list();
    }

    //
    @PostMapping("/addItem")
    public ResponseEntity<ResponseToDoList> createToDo(@Valid @RequestBody Todo todo){
        long id = 0;
        id = todoService.create(todo);
        if (id>0){
            ResponseToDoList responseToDoList = new ResponseToDoList("Item added to To-Do List", HttpStatus.CREATED);
            logger.info(responseToDoList.getMessage()+". code: "+responseToDoList.getCode());
            return new ResponseEntity<ResponseToDoList>(responseToDoList, HttpStatus.CREATED);
        } else {
            ResponseToDoList responseToDoList = new ResponseToDoList("Item Not added to To-Do list", HttpStatus.BAD_REQUEST);
            logger.info(responseToDoList.getMessage()+". code: "+ responseToDoList.getCode());
            return new ResponseEntity<ResponseToDoList>(responseToDoList, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateitem/{updateTodoItem}")
    public ResponseEntity<ResponseToDoList> updateToDo(@PathVariable Long updateTodoId, @RequestBody Todo todo){
        boolean isTodoIdValid = todoService.isToDoItemIdValid(updateTodoId);
        if (isTodoIdValid){
            long todoId = 0;
            todoId = todoService.update(updateTodoId, todo);
            if (todoId>0){
                ResponseToDoList responseToDoList = new ResponseToDoList("Item with the following title "+todo.getTodoTitle()+" updated", HttpStatus.OK);
                logger.info(responseToDoList.getMessage()+". code: "+ responseToDoList.getCode());
                return new ResponseEntity<ResponseToDoList>(responseToDoList, HttpStatus.OK);
            }else {
                ResponseToDoList responseTodoList= new ResponseToDoList("Item Not updated", HttpStatus.BAD_REQUEST);
                logger.info(responseTodoList.getMessage()+". code: "+responseTodoList.getCode());
                return new ResponseEntity<ResponseToDoList>(responseTodoList,HttpStatus.BAD_REQUEST);
            }
        } else {
            ResponseToDoList responseToDoList = new ResponseToDoList("Request not successful, invalid information provided. Please try again.", HttpStatus.NOT_FOUND);
            return new ResponseEntity<ResponseToDoList>(responseToDoList, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteitem/{deleteTodoId}")
    public ResponseEntity<ResponseToDoList> delete(@PathVariable Long deleteTodoId){
        boolean isTodoIdValid = todoService.isToDoItemIdValid(deleteTodoId);

        if (isTodoIdValid){
            todoService.delete(deleteTodoId);

            ResponseToDoList responseToDoList = new ResponseToDoList("Item deleted", HttpStatus.OK);
            logger.info(responseToDoList.getMessage()+". code: "+ responseToDoList.getCode());
            return new ResponseEntity<ResponseToDoList>(responseToDoList, HttpStatus.OK);

        } else {
            ResponseToDoList responseToDoList = new ResponseToDoList(
                    "Request not successful, invalid information provided. Please try again.",
                    HttpStatus.NOT_FOUND
            );
            return new ResponseEntity<ResponseToDoList>(responseToDoList, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/todoCount")
    public long getNumberToDoItem(){
        return todoService.getNumberToDoItem();
    }

}
