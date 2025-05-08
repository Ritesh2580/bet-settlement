package org.sporty.bet.settlement.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class RocketMQPublisher implements Publisher<Optional<String>, String> {
    private final RocketMQTemplate rocket;
    private final String rocketMQTopic = "bet-settlements";

    public RocketMQPublisher(RocketMQTemplate rocket) {
        this.rocket = rocket;
    }

    @Override
    public Optional<String> publish(String msg) {
        rocket.convertAndSend(rocketMQTopic, msg);
        log.info("🔔 Published to RocketMQ: {}", msg);
        return Optional.empty();
    }
}