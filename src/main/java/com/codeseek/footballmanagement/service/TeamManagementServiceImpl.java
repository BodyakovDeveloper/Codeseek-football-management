package com.codeseek.footballmanagement.service;

import com.codeseek.footballmanagement.dao.interfaces.PlayerDao;
import com.codeseek.footballmanagement.dao.interfaces.TeamDao;
import com.codeseek.footballmanagement.dao.interfaces.TransferHistoryDao;
import com.codeseek.footballmanagement.exception.TransferFailedException;
import com.codeseek.footballmanagement.model.Player;
import com.codeseek.footballmanagement.model.Team;
import com.codeseek.footballmanagement.model.TransferHistory;
import com.codeseek.footballmanagement.service.interfaces.TeamManagementService;
import com.codeseek.footballmanagement.util.FootballManagementUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeamManagementServiceImpl implements TeamManagementService {

    private final PlayerDao playerDao;
    private final TeamDao teamDao;
    private final TransferHistoryDao transferHistoryDao;

    public void makeTransfer(Long playerId, Long sourceTeamId, Long destinationTeamId) {
        Player player = playerDao.findById(playerId)
                .orElseThrow(() -> new TransferFailedException("Player with id: " + playerId + " not found"));

        Team sourceTeam = teamDao.findById(sourceTeamId)
                .orElseThrow(() -> new TransferFailedException("Team with id: " + sourceTeamId + " not found"));

        Team destinationTeam = teamDao.findById(destinationTeamId)
                .orElseThrow(() -> new TransferFailedException("Team with id: " + destinationTeamId + " not found"));

        caryTransfer(sourceTeam, destinationTeam, player);
    }

    private void caryTransfer(Team sourceTeam, Team destinationTeam, Player player) {
        BigDecimal moneyNeededToMakeTransfer = getMoneyRequiredToMakeTransfer(getPlayerPrice(player), sourceTeam);
        if (!isPossibleMakeTransfer(player, sourceTeam, destinationTeam, moneyNeededToMakeTransfer)) {
            throw new TransferFailedException("Impossible to make a transfer");
        }
        System.out.println(getPlayerPrice(player));
        System.out.println(getMoneyRequiredToMakeTransfer(getPlayerPrice(player), sourceTeam));

        sourceTeam.setMoney(sourceTeam.getMoney().add(getPlayerPrice(player)));
        destinationTeam.setMoney(destinationTeam.getMoney().subtract(moneyNeededToMakeTransfer));

        replacePlayerTeam(player, destinationTeam);
        teamDao.update(sourceTeam);
        teamDao.update(destinationTeam);
        writeTransferToTransferHistory(player, sourceTeam, destinationTeam, moneyNeededToMakeTransfer);
    }

    private boolean isPossibleMakeTransfer(Player player, Team sourceTeam, Team destinationTeam, BigDecimal moneyToMakeTransferNeeded) {
        if (!Objects.equals(player.getTeam().getId(), sourceTeam.getId())) {
            throw new TransferFailedException("The player: " + player.getId() + " is not in the team: " + sourceTeam.getId());
        }
        if (!isTeamHasMoneyForTransfer(destinationTeam, moneyToMakeTransferNeeded)) {
            throw new TransferFailedException("The team has no money to buy a player");
        }
        return true;
    }

    private void replacePlayerTeam(Player player, Team destinationTeam) {
        player.setTeam(destinationTeam);
        playerDao.update(player);
    }

    private void writeTransferToTransferHistory(Player player, Team sourceTeam, Team destinationTeam, BigDecimal totalDealPrice) {
        TransferHistory transferHistory = TransferHistory.builder()
                .player(player)
                .sourceTeam(sourceTeam)
                .destinationTeam(destinationTeam)
                .tax(destinationTeam.getTransferTax())
                .priceWithTax(totalDealPrice)
                .transferDate(LocalDate.now())
                .build();

        transferHistoryDao.create(transferHistory);
    }

    private BigDecimal getMoneyRequiredToMakeTransfer(BigDecimal playerPrice, Team team) {
        return FootballManagementUtil.calculatePriceWithTax(team, playerPrice);
    }

    private BigDecimal getPlayerPrice(Player player) {
        return FootballManagementUtil.calculatePlayerPrice(player);
    }

    private boolean isTeamHasMoneyForTransfer(Team team, BigDecimal priceRequiredForTransfer) {
        int result = team.getMoney().compareTo(priceRequiredForTransfer);
        if (result == 0)
            return true;
        else return result > 0;
    }
}