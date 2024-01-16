package com.jesusfc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
@Configuration
public class JmsMessageConfig {

    public static final String MY_QUEUE = "my-jms-queue";
    public static final String MY_SEND_RCV_QUEUE = "replybacktome";

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}

