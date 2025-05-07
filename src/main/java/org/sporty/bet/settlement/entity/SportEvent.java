package org.sporty.bet.settlement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_sport_event")
public class SportEvent {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", columnDefinition = "BINARY(16) DEFAULT (UUID_TO_BIN(UUID(), TRUE))")
    private UUID eventId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "start_ts")
    private Instant startTime;

    @Column(name = "end_ts")
    private Instant endTime;

    @Column(name = "event_winner_id")
    private String winnerId;

    @Column(name = "event_market_id")
    private String marketId;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bet> bets;
}