package com.codeseek.footballmanagement.dao.interfaces;

import com.codeseek.footballmanagement.model.Player;

import java.util.List;

public interface PlayerDao extends Dao<Player> {

    List<Player> findAllPlayers();
}
