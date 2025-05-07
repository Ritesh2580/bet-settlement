package org.sporty.bet.settlement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.sporty.bet.settlement.service.AbstractEventPublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class BetSettlementServiceConfig {
    private final ApplicationContext applicationContext;

    public BetSettlementServiceConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ConcurrentHashMap<String, AbstractEventPublisher> eventPublishers() {
        return applicationContext
                .getBeansOfType(AbstractEventPublisher.class)
                .values().stream()
                .collect(Collectors.toConcurrentMap(
                        AbstractEventPublisher::getTopic,
                        Function.identity(),
                        (existing, replacement) -> {
                            log.error("Duplicate event publisher found for topic : {} ", existing);
                            throw new IllegalStateException(
                                    MessageFormat.format("Multiple event publishers found for topic {0}", existing.getTopic()) // if two exist throw exception
                            );
                        },
                        ConcurrentHashMap::new
                ));
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
