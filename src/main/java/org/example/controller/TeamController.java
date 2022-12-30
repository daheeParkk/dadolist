package org.example.controller;

import org.example.domain.Team;
import org.example.dto.team.RequestTeam;
import org.example.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {

        this.teamService = teamService;

    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {

        return teamService.createTeam(team);

    }

    @GetMapping
    public List<Team> getTeamList() {

        return teamService.getTeamList();

    }

    @DeleteMapping("/{id}")
    public List<Team> deleteTeam(@PathVariable Long id) {

        return teamService.deleteTeam(id);

    }

    @PutMapping("/{id}")
    public Team changeTeamName(@PathVariable Long id, @RequestBody RequestTeam requestTeam) {

        return teamService.changeTeamName(id, requestTeam);

    }

}
