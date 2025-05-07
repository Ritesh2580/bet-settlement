package org.sporty.bet.settlement.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EventsOutcomePublishEventListener {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @KafkaListener(
            topics = "${sports.kafka.topic.outcome}",
            containerFactory = "kafkaListenerContainerFactory",
            batch = "true"
    )
    void betSettlementListeners(@Payload List<String> data) {
        data.parallelStream().forEach(record -> {
            log.info("Received record: {}", record);
        });
    }
}