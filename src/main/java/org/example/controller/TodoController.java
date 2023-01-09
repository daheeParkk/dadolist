package org.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.annotation.Permission;
import org.example.domain.Todo;
import org.example.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ApiOperation(value = "(USER) todo 작성")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @PostMapping("/user/{id}")
    public ResponseEntity<Todo> userWriteTodo(@PathVariable("id") Long userId, @RequestBody Todo todo) {

        return new ResponseEntity<>(todoService.userWriteTodo(userId, todo), HttpStatus.CREATED);
    }

    @ApiOperation(value = "todo 변경")
    @ApiImplicitParam(name = "id", value = "todo 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {

        return new ResponseEntity<>(todoService.updateTodo(id, todo), HttpStatus.OK);
    }

    @ApiOperation(value = "(USER) todo 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "유저 식별 아이디"),
            @ApiImplicitParam(name = "id", value = "todo 식별 아이디")})
    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/user/{id}/{todoId}")
    public ResponseEntity<List<Todo>> userDeleteTodo(@PathVariable("id") Long userId, @PathVariable Long todoId) {

        return new ResponseEntity<>(todoService.userDeleteTodo(userId, todoId), HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "(TEAM) todo 작성")
    @ApiImplicitParam(name = "id", value = "팀 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @PostMapping("/team/{id}")
    public ResponseEntity<Todo> teamWriteTodo(@PathVariable("id") Long teamId, @RequestBody Todo todo) {

        return new ResponseEntity<>(todoService.teamWriteTodo(teamId, todo), HttpStatus.CREATED);
    }

    @ApiOperation(value = "(TEAM) todo 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "팀 식별 아이디"),
            @ApiImplicitParam(name = "id", value = "todo 식별 아이디")})
    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/team/{id}/{todoId}")
    public ResponseEntity<List<Todo>> teamDeleteTodo(@PathVariable("id") Long teamId, @PathVariable Long todoId) {

        return new ResponseEntity<>(todoService.teamDeleteTodo(teamId, todoId), HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "todo list 조회")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/{id}")
    public ResponseEntity<List<Todo>> getTodoList(@PathVariable("id") Long userId) {

        return new ResponseEntity<>(todoService.getTodoList(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "팀 이름으로 todo list 조회")
    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping
    public ResponseEntity<List<Todo>> getTeamTodoListByTeamName(@RequestParam(value = "teamName") String teamName) {

        return new ResponseEntity<>(todoService.getTeamTodoListByTeamName(teamName), HttpStatus.OK);
    }

    @ApiOperation(value = "마감일(월)로 todo list 조회")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/month/{id}")
    public ResponseEntity<List<Todo>> getTodoListByMonth(@PathVariable("id") Long userId, @RequestParam("year") String year,
                                                         @RequestParam(value = "month") String month) {

        return new ResponseEntity<>(todoService.getTodoListByMonth(userId, year, month), HttpStatus.OK);
    }

    @ApiOperation(value = "마감일(월,일)로 todo list 조회")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/day/{id}")
    public ResponseEntity<List<Todo>> getTodoListByDays(@PathVariable("id") Long userId, @RequestParam("year") String year,
                                                        @RequestParam(value = "month") String month,
                                                        @RequestParam(value = "days") String days) {

        return new ResponseEntity<>(todoService.getTodoListByDays(userId, year, month, days), HttpStatus.OK);
    }

    @ApiOperation(value = "카테고리로 todo list 조회")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Todo>> getTodoListByCategory(@PathVariable("id") Long userId, @RequestParam(value = "category") String category) {

        return new ResponseEntity<>(todoService.getTodoListByCategory(userId, category), HttpStatus.OK);
    }

}
