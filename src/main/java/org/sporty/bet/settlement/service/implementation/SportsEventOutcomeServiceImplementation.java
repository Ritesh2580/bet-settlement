package org.sporty.bet.settlement.service.implementation;


import lombok.RequiredArgsConstructor;
import org.sporty.bet.settlement.dto.SportOutcomeEventPayload;
import org.sporty.bet.settlement.kafka.payload.SportEventOutcome;
import org.sporty.bet.settlement.mapper.EventMapper;
import org.sporty.bet.settlement.service.AbstractEventPublisher;
import org.sporty.bet.settlement.service.SportsEventOutcomeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SportsEventOutcomeServiceImplementation implements SportsEventOutcomeService {

    private final AbstractEventPublisher<SportEventOutcome> publisher;

    private final EventMapper eventMapper;

    @Override
    public void publish(final SportOutcomeEventPayload sportOutcomeEventPayload) {
        publisher.send(this.eventMapper.map(sportOutcomeEventPayload));
    }
}
