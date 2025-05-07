--liquibase formatted sql
-- changeset Ritesh:create-table-t_user splitStatements:true
CREATE TABLE t_user
(
    id         UUID         NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL,
    state      VARCHAR(255) NOT NULL,
    country    VARCHAR(255) NOT NULL
);


-- changeset Ritesh:create-table-t_sport_event splitStatements:true
CREATE TABLE t_sport_event
(
    id              UUID         NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    start_ts        TIMESTAMP,
    end_ts          TIMESTAMP,
    event_winner_id VARCHAR(255),
    event_market_id VARCHAR(255)
);

-- changeset Ritesh:create-table-t_bet splitStatements:true
CREATE TABLE t_bet
(
    c_id       UUID           NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id    VARCHAR(36),
    event_id   VARCHAR(255)   NOT NULL,
    bet_amount DECIMAL(19, 4) NOT NULL,
    CONSTRAINT fk_bet_user FOREIGN KEY (user_id) REFERENCES t_user (id),
    CONSTRAINT fk_bet_event FOREIGN KEY (event_id) REFERENCES t_sport_event (id)
);
