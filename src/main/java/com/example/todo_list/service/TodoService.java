package com.example.todo_list.service;

import com.example.todo_list.infrastructure.entity.Todo;
import com.example.todo_list.infrastructure.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TodoService {
    private static Logger logger= Logger.getLogger(TodoService.class.getName());
    private final TodoRepository todoRepository;


    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //
    public List<Todo> list(){
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return todoRepository.findAll(sort);
    }

    public Optional<Todo> GetToDoById(long id){
        return todoRepository.findById(id);
    }

    public Long create(Todo todo){
        long id = 0;
        todoRepository.save(todo);
        id = todo.getId();
        return id;
    }

    public Long update(Long id,Todo todo){
        long updateToDoId = 0;
        try {
            Todo updateToDo = todoRepository.findById(id).get();

            updateToDo.setTodoTitle(todo.getTodoTitle());
            updateToDo.setTodoDescription(todo.getTodoDescription());
            updateToDo.setTodoDate(todo.getTodoDate());
            updateToDo.setComplete(todo.isComplete());
            todoRepository.save(updateToDo);
            updateToDoId = updateToDo.getId();
            return updateToDoId;
        } catch (Exception e){
            e.printStackTrace();
        }
        return updateToDoId;
    }

    public void delete(Long id){
        todoRepository.deleteById(id);
        logger.info("Item removed from the list");
    }

    public boolean isToDoItemIdValid(long id){
        return todoRepository.findById(id).isPresent();
    }

    public long getNumberToDoItem(){
        return todoRepository.count();
    }
}
