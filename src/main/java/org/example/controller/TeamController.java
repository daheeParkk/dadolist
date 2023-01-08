package org.example.controller;

import org.example.annotation.NoAuth;
import org.example.annotation.Permission;
import org.example.domain.Team;
import org.example.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Permission(role = Permission.PermissionRole.USER)
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {

        return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.CREATED);
    }

    @NoAuth
    @GetMapping
    public ResponseEntity<List<Team>> getTeamList() {

        return new ResponseEntity<>(teamService.getTeamList(), HttpStatus.OK);
    }

    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Team>> deleteTeam(@PathVariable Long id) {

        return new ResponseEntity<>(teamService.deleteTeam(id), HttpStatus.NO_CONTENT);
    }

    @Permission(role = Permission.PermissionRole.USER)
    @PutMapping("/{id}")
    public ResponseEntity<Team> changeTeamName(@PathVariable Long id, @RequestBody Team team) {

        return new ResponseEntity<>(teamService.changeTeamName(id, team), HttpStatus.OK);
    }

}
