package org.example.service;

import org.example.domain.Todo;
import org.example.dto.todo.RequestTodo;
import org.example.repository.TodoMapper;
import org.example.repository.TodoUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;

    private final TodoUserMapper todoUserMapper;


    @Autowired
    public TodoServiceImpl(TodoMapper todoMapper, TodoUserMapper todoUserMapper) {

        this.todoMapper = todoMapper;
        this.todoUserMapper = todoUserMapper;

    }

    @Override
    public Todo userWriteTodo(Long userId, Todo todo) {

        todoMapper.writeTodo(todo);
        Long todoId = todo.getId();
        todoUserMapper.createTodoUser(userId, todoId);
        return todoMapper.getTodo(todoId);

    }

    @Override
    public Todo updateTodo(Long id, RequestTodo requestTodo) {

        Todo todo = todoMapper.getTodo(id);
        todo.update(requestTodo);
        todoMapper.updateTodo(todo);
        return todoMapper.getTodo(id);

    }

    @Override
    public List<Todo> userDeleteTodo(Long userId, Long todoId) {

        todoMapper.softDelete(true, todoId);
        todoUserMapper.softDelete(true, todoId);
        return todoMapper.getTodoList(userId);

    }

    @Override
    public Todo teamWriteTodo(Long teamId, Todo todo) {

        todoMapper.teamWriteTodo(teamId, todo);
        Long todoId = todo.getId();
        return todoMapper.getTodo(todoId);

    }

    @Override
    public List<Todo> teamDeleteTodo(Long teamId, Long todoId) {

        todoMapper.softDelete(true, todoId);
        return todoMapper.getTeamTodoListByTeamId(teamId);

    }

    @Override
    public List<Todo> getTodoList(Long userId) {
        List<Todo> todos = todoMapper.getTodoList(userId);
        return todos;

    }

    @Override
    public List<Todo> getTeamTodoListByTeamName(String teamName) {

        return todoMapper.getTeamTodoListByTeamName(teamName);

    }

    @Override
    public List<Todo> getTodoListByMonth(Long userId, String month) {

        return todoMapper.getTodoListByMonth(userId, month);

    }

    @Override
    public List<Todo> getTodoListByDays(Long userId, String month, String days) {

        return todoMapper.getTodoListByDays(userId, month, days);

    }

    @Override
    public List<Todo> getTodoListByCategory(Long userId, String category) {

        return todoMapper.getTodoListByCategory(userId, category);

    }

}
