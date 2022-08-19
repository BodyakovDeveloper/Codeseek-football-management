package com.codeseek.footballmanagement.controller;

import com.codeseek.footballmanagement.model.Team;
import com.codeseek.footballmanagement.service.interfaces.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    @ResponseBody
    public List<Team> getTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok().body(teamService.getTeamById(teamId));
    }

    @PostMapping
    public ResponseEntity<String> createTeam(@Valid @RequestBody Team team) {
        teamService.createTeam(team);
        return ResponseEntity.ok().body("Created successfully");
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<String> updateTeam(@PathVariable Long teamId, @Valid @RequestBody Team team) {
        teamService.updateTeam(teamId, team);
        return ResponseEntity.ok().body("Updated successfully");
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable("teamId") Long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok().body("Team deleted successfully");
    }
}