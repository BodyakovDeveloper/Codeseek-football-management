package com.codeseek.footballmanagement.dao.interfaces;

import com.codeseek.footballmanagement.model.Team;

import java.util.List;

public interface TeamDao extends Dao<Team> {

    List<Team> findAllTeams();
}