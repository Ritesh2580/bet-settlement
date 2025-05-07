package org.sporty.bet.settlement.kafka.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SportEventOutcome {
    private String eventId;
    private String winner;
    private Instant occurredAt;
}