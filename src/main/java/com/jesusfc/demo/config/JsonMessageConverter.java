package com.jesusfc.demo.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;

/**
 * Author Jesús Fdez. Caraballo
 * Created on feb - 2024
 */
@Component
public class JsonMessageConverter implements MessageConverter {
    private final ObjectMapper mapper;

    public JsonMessageConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    
    @Override
    public Object fromMessage(Message<?> message, Class<?> targetClass) {
        return null;
    }

    @Override
    public Message<?> toMessage(Object payload, MessageHeaders headers) {
        return null;
    }
}
