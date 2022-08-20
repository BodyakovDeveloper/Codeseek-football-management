package com.codeseek.footballmanagement.controller;

import com.codeseek.footballmanagement.model.Player;
import com.codeseek.footballmanagement.model.Team;
import com.codeseek.footballmanagement.service.interfaces.PlayerService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        return ResponseEntity.ok().body(playerService.getAllPlayers());
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long playerId) {
        return ResponseEntity.ok().body(playerService.getPlayerById(playerId));
    }

    @PostMapping
    public ResponseEntity<String> createPlayer(@Valid @RequestBody Player player, @RequestParam Long teamId) {
        Team team = teamService.getTeamById(teamId);
        player.setTeam(team);
        playerService.createPlayer(player);
        return ResponseEntity.ok().body("Successfully created");
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<String> updatePlayer(@PathVariable Long playerId, @Valid @RequestBody Player player,
                                               @RequestParam(required = false) Long teamId) {
        if (teamId != null) {
            Team newTeam = teamService.getTeamById(teamId);
            player.setTeam(newTeam);
        }
        playerService.updatePlayer(playerId, player);
        return ResponseEntity.ok().body("Updated successfully");
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable("playerId") Long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok().body("Player deleted successfully");
    }
}