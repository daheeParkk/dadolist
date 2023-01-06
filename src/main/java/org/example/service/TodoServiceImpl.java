package org.example.service;

import org.example.domain.Todo;
import org.example.dto.todo.RequestTodo;
import org.example.exception.DoesNotExistException;
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
    public void userWriteTodo(Long userId, Todo todo) {

        todoMapper.writeTodo(todo);
        Long todoId = todo.getId();
        todoUserMapper.createTodoUser(userId, todoId);

    }

    @Override
    public Todo updateTodo(Long id, RequestTodo requestTodo) {

        Todo todo = todoMapper.getTodo(id);
        if (todo == null) {
            throw new DoesNotExistException("todo not found");
        }
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
    public void teamWriteTodo(Long teamId, Todo todo) {

        todoMapper.teamWriteTodo(teamId, todo);

    }

    @Override
    public List<Todo> teamDeleteTodo(Long teamId, Long todoId) {

        todoMapper.softDelete(true, todoId);
        return todoMapper.getTeamTodoListByTeamId(teamId);

    }

    @Override
    public List<Todo> getTodoList(Long userId) {

        List<Todo> todo = todoMapper.getTodoList(userId);
        if (todo == null) {
            throw new DoesNotExistException("todo not found");
        }
        return todo;

    }

    @Override
    public List<Todo> getTeamTodoListByTeamName(String teamName) {

        List<Todo> todo = todoMapper.getTeamTodoListByTeamName(teamName);
        if (todo == null) {
            throw new DoesNotExistException("todo not found");
        }
        return todo;

    }

    @Override
    public List<Todo> getTodoListByMonth(Long userId, String month) {

        List<Todo> todo = todoMapper.getTodoListByMonth(userId, month);
        if (todo == null) {
            throw new DoesNotExistException("todo not found");
        }
        return todo;

    }

    @Override
    public List<Todo> getTodoListByDays(Long userId, String month, String days) {

        List<Todo> todo = todoMapper.getTodoListByDays(userId, month, days);
        if (todo == null) {
            throw new DoesNotExistException("todo not found");
        }
        return todo;

    }

    @Override
    public List<Todo> getTodoListByCategory(Long userId, String category) {

        List<Todo> todo = todoMapper.getTodoListByCategory(userId, category);
        if (todo == null) {
            throw new DoesNotExistException("todo not found");
        }
        return todo;

    }

}
