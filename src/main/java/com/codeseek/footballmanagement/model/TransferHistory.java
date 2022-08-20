package com.codeseek.footballmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

@Setter
@Getter
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
    @Column(name = "transfer_date", nullable = false)
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
    @Column(name = "tax", nullable = false)
    private float tax;

    @Min(1L)
    @Column(name = "price_with_tax", nullable = false)
    private BigDecimal priceWithTax;
}