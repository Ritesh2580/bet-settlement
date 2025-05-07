package org.sporty.bet.settlement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.Objects;

@Slf4j
public abstract class AbstractEventPublisher<T> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    protected AbstractEventPublisher(KafkaTemplate<String, String> kafkaTemplate,
                                     ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Internal template method.
     */
    private void publish(T event) {
        final String payload;
        try {
            payload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            log.error("Cannot serialize event : {} ", event, e);
            throw new IllegalStateException("Cannot serialize event " + event, e);
        }
        kafkaTemplate
                .send(getTopic(), getKey(event), payload)
                .whenComplete((result, ex) -> {
                    if (ex != null) handleFailure(event, ex);
                    else handleSuccess(event, result);
                });
    }

    /**
     * This is the single public entrypoint for clients.
     */
    public final void send(T data) {
        publish(data);
    }

    public abstract String getTopic();

    public abstract String getKey(T event);

    protected void handleSuccess(T event, SendResult<String, String> metadata) {
        log.info("Published event {} to partition {} @ offset {}",
                event,
                metadata.getRecordMetadata().partition(),
                metadata.getRecordMetadata().offset());
    }

    protected void handleFailure(T event, Throwable ex) {
        log.error("Failed to publish {}: {}", event, ex.getMessage());
    }
}