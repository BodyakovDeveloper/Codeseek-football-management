package com.codeseek.footballmanagement.service;

import com.codeseek.footballmanagement.dao.interfaces.TeamDao;
import com.codeseek.footballmanagement.mapper.TeamMapper;
import com.codeseek.footballmanagement.model.Team;
import com.codeseek.footballmanagement.service.interfaces.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamDao teamDao;
    private final TeamMapper teamMapper;

    public Team getTeamById(Long id) {
        return teamDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Team with id" + id + " not found"));
    }

    public void updateTeam(Long teamId, Team team) {
        Team teamFromDb = getTeamById(teamId);
        teamMapper.updateTeam(teamFromDb, team);

        teamDao.update(teamFromDb);
    }

    public void deleteTeam(Long id) {
        Team team = getTeamById(id);
        teamDao.remove(team);
    }

    public void createTeam(Team team) {
        teamDao.create(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamDao.findAllTeams();
    }
}