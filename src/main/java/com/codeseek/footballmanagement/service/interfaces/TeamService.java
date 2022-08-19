package com.codeseek.footballmanagement.service.interfaces;

import com.codeseek.footballmanagement.model.Team;

import java.util.List;

public interface TeamService {
    Team getTeamById(Long id);

    void updateTeam(Long teamId, Team Team);

    void deleteTeam(Long id);

    void createTeam(Team Team);

    List<Team> getAllTeams();
}