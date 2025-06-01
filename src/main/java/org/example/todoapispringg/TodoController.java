package org.example.todoapispringg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


    private TodoService todoService;//composition
    private TodoService todoService2;//composition


    private static final String TODO_NOT_FOUND = "Todo Not Found";
    private static List<ToDo> todoList;

    public TodoController( @Qualifier("AnotherTodoService") TodoService todoService,
                           @Qualifier("FakeTodoService") TodoService todoService2) {
        this.todoService = todoService;
        this.todoService2 = todoService2;
        todoList = new ArrayList<>();
        todoList.add(new ToDo(1,false,"Todo 1",1));
        todoList.add(new ToDo(2,true,"Todo 2",2));
    }


    @GetMapping
    public ResponseEntity<List<ToDo>>
            getTodos(@RequestParam(required = false, defaultValue = "true") Boolean isCompleted) {
        System.out.println("Incoming query parameters: " + isCompleted + " " +this.todoService2.doSomething());
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping
    public ResponseEntity<ToDo> createTodo(@RequestBody ToDo newToDo) {
        todoList.add(newToDo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newToDo);
    }
    @GetMapping("/{todoId}")
    //wildcard
    public ResponseEntity<?> getTodobyId(@PathVariable Long todoId) {
        for(ToDo todo: todoList) {
            if(todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }
}
