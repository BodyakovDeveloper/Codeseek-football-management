CREATE TABLE teams
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    team_name        VARCHAR(32)           NOT NULL,
    coach_first_name VARCHAR(32)           NOT NULL,
    coach_last_name  VARCHAR(32)           NOT NULL,
    money            DECIMAL               NOT NULL,
    transfer_tax     FLOAT                 NOT NULL,
    CONSTRAINT pk_teams PRIMARY KEY (id)
);

CREATE TABLE players
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    first_name            VARCHAR(32)           NOT NULL,
    last_name             VARCHAR(32)           NOT NULL,
    birthday              date                  NOT NULL,
    career_beginning_date date                  NOT NULL,
    team_id               BIGINT                NULL,
    CONSTRAINT pk_players PRIMARY KEY (id)
);

CREATE TABLE transfer_history
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    transfer_date       date                  NOT NULL,
    source_team_id      BIGINT                NOT NULL,
    destination_team_id BIGINT                NOT NULL,
    player_id           BIGINT                NOT NULL,
    tax                 FLOAT                 NOT NULL,
    price_with_tax      DECIMAL               NOT NULL,
    CONSTRAINT pk_transfer_history PRIMARY KEY (id)
);

ALTER TABLE teams
    ADD CONSTRAINT uc_teams_team_name UNIQUE (team_name);

ALTER TABLE players
    ADD CONSTRAINT FK_PLAYERS_ON_TEAM FOREIGN KEY (team_id) REFERENCES teams (id);

ALTER TABLE transfer_history
    ADD CONSTRAINT FK_TRANSFER_HISTORY_ON_DESTINATION_TEAM FOREIGN KEY (destination_team_id) REFERENCES teams (id);

ALTER TABLE transfer_history
    ADD CONSTRAINT FK_TRANSFER_HISTORY_ON_PLAYER FOREIGN KEY (player_id) REFERENCES players (id);

ALTER TABLE transfer_history
    ADD CONSTRAINT FK_TRANSFER_HISTORY_ON_SOURCE_TEAM FOREIGN KEY (source_team_id) REFERENCES teams (id);


INSERT INTO teams (id, team_name, coach_first_name, coach_last_name, money, transfer_tax)
values (default, 'Manchester City', 'Josep', 'Guardiola', '140000000', '4');

INSERT INTO teams (id, team_name, coach_first_name, coach_last_name, money, transfer_tax)
values (default, 'Arsenal', 'Mikel', 'Arteta', '100000000', '2');

INSERT INTO players (id, first_name, last_name, birthday, career_beginning_date, team_id)
values (default, 'Bohdan', 'Koval', '2002-09-23', '2020-05-01', 1);

INSERT INTO players (id, first_name, last_name, birthday, career_beginning_date, team_id)
values (default, 'Codeseek', 'Software', '2001-01-03', '2015-02-05', 2);