--liquibase formatted sql
-- changeset Ritesh:insert-sample-users splitStatements:true
INSERT INTO t_user (
    first_name, last_name, email, phone, address, city, state, country
) VALUES (
             'Rahul', 'Sharma', 'rahul.sharma@example.com', '9123456780',
             '12 MG Road', 'Mumbai', 'Maharashtra', 'India'
         );
INSERT INTO t_user (
    first_name, last_name, email, phone, address, city, state, country
) VALUES (
             'Priya', 'Singh', 'priya.singh@example.com', '9234567890',
             '34 Nehru Street', 'Delhi', 'Delhi', 'India'
         );
INSERT INTO t_user (
    first_name, last_name, email, phone, address, city, state, country
) VALUES (
             'Vikram', 'Patel', 'vikram.patel@example.com', '9345678901',
             '56 Gandhi Marg', 'Ahmedabad', 'Gujarat', 'India'
         );
INSERT INTO t_user (
    first_name, last_name, email, phone, address, city, state, country
) VALUES (
             'Anjali', 'Kumar', 'anjali.kumar@example.com', '9456789012',
             '78 Ashok Road', 'Bengaluru', 'Karnataka', 'India'
         );
INSERT INTO t_user (
    first_name, last_name, email, phone, address, city, state, country
) VALUES (
             'Rohit', 'Gupta', 'rohit.gupta@example.com', '9567890123',
             '90 MG Road', 'Chennai', 'Tamil Nadu', 'India'
         );

-- changeset Ritesh:insert-sample-sport-events splitStatements:true
INSERT INTO t_sport_event (
    name, start_ts, end_ts, event_winner_id, event_market_id
) VALUES (
             'Football Finals',
             TIMESTAMP '2025-06-01 18:00:00',
             TIMESTAMP '2025-06-01 20:00:00',
             NULL, NULL
         );
INSERT INTO t_sport_event (
    name, start_ts, end_ts, event_winner_id, event_market_id
) VALUES (
             'Basketball Semis',
             TIMESTAMP '2025-06-02 19:30:00',
             TIMESTAMP '2025-06-02 21:30:00',
             NULL, NULL
         );
INSERT INTO t_sport_event (
    name, start_ts, end_ts, event_winner_id, event_market_id
) VALUES (
             'Tennis Open',
             TIMESTAMP '2025-06-03 14:00:00',
             TIMESTAMP '2025-06-03 17:00:00',
             NULL, NULL
         );
INSERT INTO t_sport_event (
    name, start_ts, end_ts, event_winner_id, event_market_id
) VALUES (
             'Hockey League',
             TIMESTAMP '2025-06-04 16:00:00',
             TIMESTAMP '2025-06-04 18:00:00',
             NULL, NULL
         );
INSERT INTO t_sport_event (
    name, start_ts, end_ts, event_winner_id, event_market_id
) VALUES (
             'Cricket Test',
             TIMESTAMP '2025-06-05 10:00:00',
             TIMESTAMP '2025-06-05 18:00:00',
             NULL, NULL
         );

-- changeset Ritesh:insert-sample-bets splitStatements:true
INSERT INTO t_bet (user_id, event_id, bet_amount)
SELECT u.id, e.id, 100.00
FROM t_user u
         JOIN t_sport_event e ON e.name = 'Football Finals'
WHERE u.email = 'rahul.sharma@example.com';
INSERT INTO t_bet (user_id, event_id, bet_amount)
SELECT u.id, e.id, 150.50
FROM t_user u
         JOIN t_sport_event e ON e.name = 'Basketball Semis'
WHERE u.email = 'priya.singh@example.com';
INSERT INTO t_bet (user_id, event_id, bet_amount)
SELECT u.id, e.id, 75.25
FROM t_user u
         JOIN t_sport_event e ON e.name = 'Tennis Open'
WHERE u.email = 'vikram.patel@example.com';
INSERT INTO t_bet (user_id, event_id, bet_amount)
SELECT u.id, e.id, 200.00
FROM t_user u
         JOIN t_sport_event e ON e.name = 'Hockey League'
WHERE u.email = 'anjali.kumar@example.com';
INSERT INTO t_bet (user_id, event_id, bet_amount)
SELECT u.id, e.id, 125.75
FROM t_user u
         JOIN t_sport_event e ON e.name = 'Cricket Test'
WHERE u.email = 'rohit.gupta@example.com';
