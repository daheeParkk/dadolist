package org.example.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamUserMapper {

    void joinTeam(Long id, Long teamId);

    void softDelete(@Param("isDeleted") Boolean isDeleted, @Param("id") Long id);

}
