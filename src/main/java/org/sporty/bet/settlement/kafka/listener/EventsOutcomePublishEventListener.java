package org.sporty.bet.settlement.kafka.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sporty.bet.settlement.dto.BetSettlementPayload;
import org.sporty.bet.settlement.entity.Bet;
import org.sporty.bet.settlement.kafka.payload.SportEventOutcome;
import org.sporty.bet.settlement.service.BetService;
import org.sporty.bet.settlement.service.RocketMQPublisher;
import org.sporty.bet.settlement.util.Converter;
import org.springframework.data.util.Pair;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventsOutcomePublishEventListener {

    private final BetService betService;
    private final Converter converter = new Converter();
    private final RocketMQPublisher rocketMQPublisher;

    @KafkaListener(topics = "${sports.kafka.topic.outcome}", containerFactory = "kafkaListenerContainerFactory", batch = "true")
    void betSettlementListeners(@Payload List<String> data) {
        Class<SportEventOutcome> convertToType = SportEventOutcome.class;
        data.parallelStream()
                .map(record -> {
                    return converter.convert(record, convertToType);
                })
                .map(record -> {
                    Optional<Bet> bet = betService.findByEventId(UUID.fromString(record.getEventId()));
                    return bet.map(value -> Pair.of(UUID.fromString(record.getWinnerId()), value)).orElse(null);
                })
                .filter(Objects::nonNull)
                .forEach(record -> rocketMQPublisher.publish(converter.convert(preparePayload(record))));
    }

    private BetSettlementPayload preparePayload(Pair<UUID, Bet> bet) {
        final BetSettlementPayload payload = new BetSettlementPayload();
        payload.setEventId(bet.getSecond().getEvent().getEventId());
        payload.setWinnerId(bet.getFirst());
        payload.setBetId(bet.getSecond().getId());
        return payload;
    }
}