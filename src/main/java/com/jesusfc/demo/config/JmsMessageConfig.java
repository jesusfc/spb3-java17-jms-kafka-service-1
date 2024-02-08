package com.jesusfc.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Author Jesús Fdez. Caraballo
 * Created on Ene - 2024
 */
@Configuration
public class JmsMessageConfig {

    public static final String MY_QUEUE = "my-jms-queue";
    public static final String MY_SEND_RCV_QUEUE = "reply_back_to_me";


    @Bean(name = "artemisConnectionFactory")
    public ConnectionFactory artemisConnectionFactory() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
        connectionFactory.setUser("artemis");
        connectionFactory.setPassword("artemis");
        connectionFactory.setCallTimeout(2000);
        return connectionFactory;
    }

    @Bean(name = "artemisJmsTemplate")
    public JmsTemplate artemisJmsTemplate() throws JMSException {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(artemisConnectionFactory());
        return template;
    }


    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}

