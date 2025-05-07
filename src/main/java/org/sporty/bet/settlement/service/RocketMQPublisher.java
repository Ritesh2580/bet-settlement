package org.sporty.bet.settlement.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

import java.util.Optional;

@Slf4j
public class RocketMQPublisher implements Publisher<Optional<String>, String> {
    private final RocketMQTemplate rocket;

    public RocketMQPublisher(RocketMQTemplate rocket) {
        this.rocket = rocket;
    }

    @Override
    public Optional<String> publish(String msg) {
        rocket.convertAndSend("rocket-topic", msg);
        log.info("🔔 Published to RocketMQ: {}", msg);
        return Optional.empty();
    }
}