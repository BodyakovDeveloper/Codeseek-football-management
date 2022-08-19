package com.codeseek.footballmanagement.service;

import com.codeseek.footballmanagement.dao.interfaces.PlayerDao;
import com.codeseek.footballmanagement.mapper.PlayerMapper;
import com.codeseek.footballmanagement.model.Player;
import com.codeseek.footballmanagement.service.interfaces.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerDao playerDao;
    private final PlayerMapper playerMapper;

    public Player getPlayerById(Long id) {
        return playerDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Player with id: " + id + " not found"));
    }

    public void updatePlayer(Long playerId, Player player) {
        Player playerFromDb = getPlayerById(playerId);
        playerMapper.updatePlayer(playerFromDb, player);

        playerDao.update(playerFromDb);
    }

    public void deletePlayer(Long id) {
        Player player = getPlayerById(id);
        playerDao.remove(player);
    }

    public void createPlayer(Player player) {
        playerDao.create(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerDao.findAllPlayers();
    }
}