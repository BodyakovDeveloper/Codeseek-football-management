package com.codeseek.footballmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 32)
    @Pattern(regexp = "^[A-Za-z\\s]*$")
    @Column(name = "team_name", unique = true, nullable = false, length = 32)
    private String teamName;

    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Should be a right name")
    @Column(name = "coach_first_name", nullable = false, length = 32)
    private String coachFirstName;

    @Size(min = 1, max = 32)
    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Should be a right last name")
    @Column(name = "coach_last_name", nullable = false, length = 32)
    private String coachLastName;

    @Min(0L)
    @Column(nullable = false)
    private BigDecimal money;

    @Min(1L)
    @Max(10L)
    @Column(name = "transfer_tax" ,nullable = false)
    private float transferTax;

    @JsonIgnore
    @Setter(AccessLevel.PRIVATE)
    @OneToMany
    @JoinColumn(name = "team_id", referencedColumnName = "id", updatable = false)
    private List<Player> players = new ArrayList<>();
}