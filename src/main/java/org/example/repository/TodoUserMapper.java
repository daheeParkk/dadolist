package org.example.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoUserMapper {

    void createTodoUser(@Param("userId") Long userId, @Param("todoId") Long todoId);

    void softDelete(@Param("isDeleted") Boolean isDeleted, @Param("id") Long id);

}
