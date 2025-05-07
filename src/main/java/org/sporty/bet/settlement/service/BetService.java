package org.sporty.bet.settlement.service;

import lombok.RequiredArgsConstructor;
import org.sporty.bet.settlement.repository.BetRepository;

@RequiredArgsConstructor
public class BetService {
    private final BetRepository betRepository;
}
