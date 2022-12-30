package org.example.controller;

import org.example.domain.Todo;
import org.example.dto.todo.GetTodoList;
import org.example.dto.todo.RequestTodo;
import org.example.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {

        this.todoService = todoService;

    }

    @PostMapping("/user/{id}")
    public Todo userWriteTodo(@PathVariable("id") Long userId, @RequestBody Todo todo) {

        return todoService.userWriteTodo(userId, todo);

    }

    @PatchMapping("/user/{id}")
    public Todo userUpdateTodo(@PathVariable Long id, @RequestBody RequestTodo requestTodo) {

        return todoService.userUpdateTodo(id, requestTodo);

    }

    @DeleteMapping("/user/{id}/{todoId}")
    public List<Todo> userDeleteTodo(@PathVariable("id") Long userId, @PathVariable Long todoId) {

        return todoService.userDeleteTodo(userId,todoId);

    }

    @PostMapping("/team/{id}")
    public Todo teamWriteTodo(@PathVariable("id") Long teamId, @RequestBody Todo todo){

        return todoService.teamWriteTodo(teamId, todo);

    }

    @PatchMapping("/team/{id}")
    public Todo teamUpdateTodo(@PathVariable Long id, @RequestBody RequestTodo requestTodo){

        return todoService.teamUpdateTodo(id, requestTodo);

    }

    @DeleteMapping("/team/{id}/{todoId}")
    public List<Todo> teamDeleteTodo(@PathVariable("id") Long teamId, @PathVariable Long todoId){

        return todoService.teamDeleteTodo(teamId, todoId);

    }

    @GetMapping("/{id}")
    public List<Todo> getTodoList(@PathVariable("id") Long userId){

        return todoService.getTodoList(userId);

    }

    @GetMapping("/team")
    public List<Todo> getTeamTodoListByTeamName(@RequestParam(value = "teamName") String teamName){

        return todoService.getTeamTodoListByTeamName(teamName);

    }

    @GetMapping("/month/{id}")
    public List<Todo> getTodoListByMonth(@PathVariable("id") Long userId, @RequestParam(value = "month") String month){

        return todoService.getTodoListByMonth(userId, month);

    }

    @GetMapping("/day/{id}")
    public List<Todo> getTodoListByDays(@PathVariable("id") Long userId, @RequestParam(value = "month") String month,
                                         @RequestParam(value = "days") String days){

        return todoService.getTodoListByDays(userId, month, days);

    }

    @GetMapping("/category/{id}")
    public List<Todo> getTodoListByCategory(@PathVariable("id") Long userId, @RequestParam(value = "category") String category){

        return todoService.getTodoListByCategory(userId, category);

    }

}
