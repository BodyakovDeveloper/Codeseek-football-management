package com.codeseek.footballmanagement.controller;

import com.codeseek.footballmanagement.service.interfaces.TeamManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transfer")
@RequiredArgsConstructor
public class TeamManagementController {

    private final TeamManagementService teamManagementService;

    @PostMapping
    public ResponseEntity<String> doTransfer(@RequestParam Long playerId,
                                             @RequestParam Long sourceTeamId,
                                             @RequestParam Long destinationTeamId) {
        teamManagementService.makeTransfer(playerId, sourceTeamId, destinationTeamId);
        return ResponseEntity.ok().body("The transfer was successful");
    }
}