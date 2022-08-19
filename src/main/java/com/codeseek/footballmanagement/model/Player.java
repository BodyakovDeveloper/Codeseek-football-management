package com.codeseek.footballmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Should be a right name")
    @Size(min = 1, max = 32)
    @Column(nullable = false, name = "first_name", length = 32)
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Should be a right last name")
    @Size(min = 1, max = 32)
    @Column(nullable = false, name = "last_name", length = 32)
    private String lastName;

    @PastOrPresent(message = "Should be a right birthday")
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @PastOrPresent(message = "Should be a right career beginning date")
    @Column(name = "career_beginning_date", nullable = false)
    private LocalDate careerBeginningDate;

    @ManyToOne
    private Team team;
}