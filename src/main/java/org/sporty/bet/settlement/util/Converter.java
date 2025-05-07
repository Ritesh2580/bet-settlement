package org.sporty.bet.settlement.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.sporty.bet.settlement.exception.ConversionException;

@Slf4j
public class Converter {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T convert(String data, Class<T> clazz) {
        try {
            return mapper.reader().readValue(data, clazz);
        } catch (Throwable e) {
            log.error("Unable to convert data to {}", clazz.toGenericString(), e);
            throw new ConversionException();
        }
    }

    public <T> String convert(T data) {
        try {
            return mapper.writer().writeValueAsString(data);
        } catch (Throwable e) {
            log.error("Unable to convert data {}", data);
            throw new ConversionException();
        }
    }

}
