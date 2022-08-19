package com.codeseek.footballmanagement.util;

import com.codeseek.footballmanagement.model.Player;
import com.codeseek.footballmanagement.model.Team;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import static java.lang.String.valueOf;

public class FootballManagementUtil {

    private static final BigDecimal ONE_HUNDRED_THOUSAND = new BigDecimal(valueOf(100_000));
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(valueOf(100));
    private static final int MONTHS_IN_YEAR = 12;

    public static BigDecimal calculatePlayerPrice(Player player) {
        BigDecimal monthsExperience = new BigDecimal(valueOf(getMonthsExperience(player)));
        BigDecimal playerAge = new BigDecimal(valueOf(getAge(player)));

        return monthsExperience
                .multiply(ONE_HUNDRED_THOUSAND)
                .divide(playerAge, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculatePriceWithTax(Team team, BigDecimal price) {
        BigDecimal transferTax = new BigDecimal(
                valueOf(team.getTransferTax())
        );

        transferTax = transferTax
                .divide(ONE_HUNDRED, RoundingMode.HALF_UP)
                .multiply(price);

        return price.add(transferTax);
    }

    private static int getMonthsExperience(Player player) {
        int years = Period.between(player.getBirthday(), LocalDate.now()).getYears();
        return years * MONTHS_IN_YEAR + Period.between(player.getCareerBeginningDate(), LocalDate.now()).getMonths();
    }

    private static int getAge(Player player) {
        return Period.between(player.getBirthday(), LocalDate.now()).getYears();
    }
}