package org.example.service;

import org.example.domain.Todo;
import org.example.dto.todo.GetTodoList;
import org.example.dto.todo.RequestTodo;

import java.util.List;

public interface TodoService {

    Todo userWriteTodo(Long userId, Todo todo);

    Todo updateTodo(Long id, RequestTodo requestTodo);

    List<Todo> userDeleteTodo(Long userId, Long todoId);

    Todo teamWriteTodo(Long teamId, Todo todo);

    List<Todo> teamDeleteTodo(Long teamId, Long todoId);

    List<Todo> getTodoList(Long userId);

    List<Todo> getTeamTodoListByTeamName(String teamName);

    List<Todo> getTodoListByMonth(Long userId, String month);

    List<Todo> getTodoListByDays(Long userId, String month, String days);

    List<Todo> getTodoListByCategory(Long userId, String category);

}
