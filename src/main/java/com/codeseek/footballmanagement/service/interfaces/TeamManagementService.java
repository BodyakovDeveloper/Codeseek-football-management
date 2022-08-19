package com.codeseek.footballmanagement.service.interfaces;

public interface TeamManagementService {
    void makeTransfer(Long playerId, Long sourceTeamId, Long destinationTeamId);
}