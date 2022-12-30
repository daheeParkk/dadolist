package org.example.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTeamMapper {

    void createTodoTeam(Long teamId, Long todoId);

    void softDelete(@Param("isDeleted") Boolean isDeleted, @Param("id") Long id);

}
