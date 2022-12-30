package org.example.repository;

import org.apache.ibatis.annotations.Param;
import org.example.domain.Todo;
import org.example.dto.todo.GetTodoList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoMapper {

    void writeTodo(Todo todo);

    Todo getTodo(Long todoId);

    List<Todo> getTodoList(Long userId);

    List<Todo> getTeamTodoListByTeamId(Long teamId);

    List<Todo> getTeamTodoListByTeamName(String teamName);

    List<Todo> getTodoListByMonth(@Param("userId") Long userId, @Param("month") String month);

    List<Todo> getTodoListByDays(@Param("userId") Long userId, @Param("month") String month, @Param("days") String days);

    List<Todo> getTodoListByCategory(@Param("userId") Long userId, @Param("category") String category);

    void updateTodo(Todo todo);

    void softDelete(@Param("isDeleted") Boolean isDeleted, @Param("id") Long id);

}
