package org.sporty.bet.settlement.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class ExistBetDecorator implements Publisher<Optional<String>, String> {

    private final Publisher delegate;
    private final MyRepository repo;

    public ExistBetDecorator(Publisher delegate, MyRepository repo) {
        this.delegate = delegate;
        this.repo = repo;
    }

    @Override
    public Optional<String> publish(String msg) {
        if (repo.existsByKey(msg)) {
            log.debug("✅ {} exists in DB; delegating to publisher", msg);
            delegate.publish(msg);
        } else {
            log.debug("⛔ {} not found in DB; skipping", msg);
        }
        return Optional.empty();
    }
}
