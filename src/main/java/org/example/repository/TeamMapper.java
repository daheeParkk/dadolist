package org.example.repository;

import org.apache.ibatis.annotations.Param;
import org.example.domain.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    Team selectTeamByTeamName(String teamName);

    Team getTeam(Long id);

    void createTeam(Team team);

    List<Team> getTeamList();

    void softDelete(@Param("isDeleted") Boolean isDeleted, @Param("id") Long id);

    void updateTeam(Team team);

}
