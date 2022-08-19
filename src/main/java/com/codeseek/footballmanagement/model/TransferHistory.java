package com.codeseek.footballmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transfer_history")
public class TransferHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    @Column(nullable = false)
    private LocalDate transferDate;

    @OneToOne
    @JoinColumn(name = "source_team_id", referencedColumnName = "id", updatable = false, nullable = false)
    private Team sourceTeam;

    @OneToOne
    @JoinColumn(name = "destination_team_id", referencedColumnName = "id", updatable = false, nullable = false)
    private Team destinationTeam;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", updatable = false, nullable = false)
    private Player player;

    @Min(1L)
    @Max(10L)
    @Column(nullable = false)
    private float tax;

    @Min(1L)
    @Column(nullable = false)
    private BigDecimal priceWithTax;
}