package org.sporty.bet.settlement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sporty.bet.settlement.entity.Bet;
import org.sporty.bet.settlement.repository.BetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BetService {
    private final BetRepository betRepository;

    public Optional<Bet> findByEventId(UUID eventId) {
        log.info("Searching for bet with eventId {}", eventId);
        return betRepository.findByEventEventId(eventId);
    }
}
