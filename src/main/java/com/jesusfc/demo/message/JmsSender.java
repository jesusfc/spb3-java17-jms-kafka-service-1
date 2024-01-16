package com.jesusfc.demo.message;

import com.jesusfc.demo.config.JmsMessageConfig;
import com.jesusfc.demo.model.JmsMessage;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
@AllArgsConstructor
@Component
public class JmsSender {

    private JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {

        System.out.println("Test Message Scheduled");

        JmsMessage message = JmsMessage
                .builder()
                .uuid(UUID.randomUUID())
                .message("Test Message Scheduled")
                .build();

        jmsTemplate.convertAndSend(JmsMessageConfig.MY_QUEUE, message);

        System.out.println("Scheduled Message Sent!");

    }

    public void sendMessage(String message) {

        System.out.println(message);

        JmsMessage jmsMessage = JmsMessage
                .builder()
                .uuid(UUID.randomUUID())
                .message(message)
                .build();

        jmsTemplate.convertAndSend(JmsMessageConfig.MY_QUEUE, jmsMessage);

        System.out.println("Message Sent!");

    }


}
