package org.example.service;

import org.example.domain.Team;

import java.util.List;

public interface TeamService {

    Team createTeam(Team team);

    List<Team> getTeamList();

    List<Team> deleteTeam(Long id);

    Team changeTeamName(Long id, Team team);

}
