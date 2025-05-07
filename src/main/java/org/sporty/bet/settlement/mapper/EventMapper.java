package org.sporty.bet.settlement.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.sporty.bet.settlement.dto.SportOutcomeEventPayload;
import org.sporty.bet.settlement.kafka.payload.SportEventOutcome;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    private final ModelMapper modelMapper;

    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(new PropertyMap<SportOutcomeEventPayload, SportEventOutcome>() {
            @Override
            protected void configure() {

            }
        });
    }

    public SportEventOutcome map(SportOutcomeEventPayload payload) {
        return modelMapper.map(payload, SportEventOutcome.class);
    }
}
