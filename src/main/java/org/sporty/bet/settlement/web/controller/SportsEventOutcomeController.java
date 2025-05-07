package org.sporty.bet.settlement.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sporty.bet.settlement.service.SportsEventOutcomeService;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SportsEventOutcomeController implements SportsEventOutcomeApi {

    private final SportsEventOutcomeService delegate;

    @Override
    public SportsEventOutcomeService getDelegate() {
        return this.delegate;
    }
}
