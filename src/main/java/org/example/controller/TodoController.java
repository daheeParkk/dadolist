package org.example.controller;

import org.example.annotation.Permission;
import org.example.domain.Todo;
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

    @Permission(role = Permission.PermissionRole.USER)
    @PostMapping("/user/{id}")
    public void userWriteTodo(@PathVariable("id") Long userId, @RequestBody Todo todo) {

        todoService.userWriteTodo(userId, todo);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody RequestTodo requestTodo) {

        return todoService.updateTodo(id, requestTodo);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/user/{id}/{todoId}")
    public List<Todo> userDeleteTodo(@PathVariable("id") Long userId, @PathVariable Long todoId) {

        return todoService.userDeleteTodo(userId,todoId);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @PostMapping("/team/{id}")
    public void teamWriteTodo(@PathVariable("id") Long teamId, @RequestBody Todo todo){

        todoService.teamWriteTodo(teamId, todo);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/team/{id}/{todoId}")
    public List<Todo> teamDeleteTodo(@PathVariable("id") Long teamId, @PathVariable Long todoId){

        return todoService.teamDeleteTodo(teamId, todoId);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/{id}")
    public List<Todo> getTodoList(@PathVariable("id") Long userId){

        return todoService.getTodoList(userId);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/team")
    public List<Todo> getTeamTodoListByTeamName(@RequestParam(value = "teamName") String teamName){

        return todoService.getTeamTodoListByTeamName(teamName);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/month/{id}")
    public List<Todo> getTodoListByMonth(@PathVariable("id") Long userId, @RequestParam(value = "month") String month){

        return todoService.getTodoListByMonth(userId, month);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/day/{id}")
    public List<Todo> getTodoListByDays(@PathVariable("id") Long userId, @RequestParam(value = "month") String month,
                                         @RequestParam(value = "days") String days){

        return todoService.getTodoListByDays(userId, month, days);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/category/{id}")
    public List<Todo> getTodoListByCategory(@PathVariable("id") Long userId, @RequestParam(value = "category") String category){

        return todoService.getTodoListByCategory(userId, category);

    }

}
