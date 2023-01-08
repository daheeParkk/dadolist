package org.example.service;

import org.example.domain.Team;
import org.example.repository.TeamMapper;
import org.example.repository.TeamUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamMapper teamMapper;

    private final TeamUserMapper teamUserMapper;

    @Autowired
    public TeamServiceImpl(TeamMapper teamMapper, TeamUserMapper teamUserMapper) {

        this.teamMapper = teamMapper;
        this.teamUserMapper = teamUserMapper;
    }

    @Override
    public Team createTeam(Team team) {

        teamMapper.createTeam(team);

        return teamMapper.getTeam(team.getId());
    }

    @Override
    public List<Team> getTeamList() {

        return teamMapper.getTeamList();
    }

    @Transactional
    @Override
    public List<Team> deleteTeam(Long id) {

        teamMapper.softDelete(true, id);
        teamUserMapper.softDelete(true, id);

        return teamMapper.getTeamList();
    }

    @Override
    public Team changeTeamName(Long id, Team team) {

        Team changedTeamName = Team.builder()
                .id(id)
                .teamName(team.getTeamName())
                .build();
        teamMapper.updateTeam(changedTeamName);

        return teamMapper.getTeam(id);
    }

}
