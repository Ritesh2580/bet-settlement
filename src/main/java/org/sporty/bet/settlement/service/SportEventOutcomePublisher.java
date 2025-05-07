package org.sporty.bet.settlement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.sporty.bet.settlement.kafka.payload.SportEventOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SportEventOutcomePublisher extends AbstractEventPublisher<SportEventOutcome> {

    @Autowired
    public SportEventOutcomePublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        super(kafkaTemplate, objectMapper);
    }


    @Override
    public String getTopic() {
        return "event-outcomes";
    }

    @Override
    public String getKey(SportEventOutcome event) {
        return event.getEventId().toString();
    }


    protected void handleSuccess(SportEventOutcome event, SendResult<String, String> metadata) {
        log.info("Published event {} to partition {} @ offset {}",
                event,
                metadata.getRecordMetadata().partition(),
                metadata.getRecordMetadata().offset()
        );
    }

    protected void handleFailure(SportEventOutcome event, Throwable ex) {
        //do something based on failure
        log.error("Failed to publish {}: {}",
                event,
                ex.getMessage()
        );
    }
}
