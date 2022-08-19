package com.codeseek.footballmanagement.service.interfaces;

import com.codeseek.footballmanagement.model.Player;

import java.util.List;

public interface PlayerService {
    Player getPlayerById(Long id);

    void updatePlayer(Long playerId, Player player);

    void deletePlayer(Long id);

    void createPlayer(Player player);

    List<Player> getAllPlayers();
}