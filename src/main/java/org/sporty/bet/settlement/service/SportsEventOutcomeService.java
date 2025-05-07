package org.sporty.bet.settlement.service;

import org.sporty.bet.settlement.dto.SportOutcomeEventPayload;

public interface SportsEventOutcomeService {
    void publish(final SportOutcomeEventPayload sportOutcomeEventPayload);
}

