package org.sporty.bet.settlement.web.controller;

import jakarta.validation.Valid;
import org.sporty.bet.settlement.dto.SportOutcomeEventPayload;
import org.sporty.bet.settlement.service.SportsEventOutcomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@RequestMapping("/v1/events/sports")
public interface SportsEventOutcomeApi {
    SportsEventOutcomeService getDelegate();

    @PostMapping(value = "/publish")
    default ResponseEntity<Void> publish(
            @Valid @RequestBody final SportOutcomeEventPayload sportOutcomeEventPayload
    ) {
        getDelegate().publish(sportOutcomeEventPayload);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
