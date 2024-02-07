package com.jesusfc.demo.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusfc.demo.config.JmsMessageConfig;
import com.jesusfc.demo.model.JmsMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
@AllArgsConstructor
@Component
public class JmsSender {

    private JmsTemplate jmsTemplate;
    private ObjectMapper objectMapper;

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void sendMessage() {
        try {
            System.out.println("Message  1 - Test Message Scheduled");

            JmsMessage message = JmsMessage
                    .builder()
                    .uuid(UUID.randomUUID())
                    .to("jfcaraballo@gmail.com")
                    .message("Message Scheduled convert and send, Queue: " + JmsMessageConfig.MY_QUEUE + ", " + LocalDateTime.now())
                    .body("Body Test 1 Message Scheduled convert and send, Queue: " + JmsMessageConfig.MY_QUEUE + ", " + LocalDateTime.now())
                    .build();

            jmsTemplate.convertAndSend(JmsMessageConfig.MY_QUEUE, message);
            System.out.println("Message 1 - Scheduled Message Sent!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //  @Scheduled(fixedRate = 10000) // every 10 seconds
    public void sendAndReplyToMeMessage() throws JMSException {

        System.out.println("Message 2 - Test Message Scheduled");

        JmsMessage message = JmsMessage
                .builder()
                .uuid(UUID.randomUUID())
                .to("jfcaraballo@gmail.com")
                .message("Message Scheduled send and received, Queue: " + JmsMessageConfig.MY_SEND_RCV_QUEUE + ", " + LocalDateTime.now())
                .body("Body Test 2 Message Scheduled convert and send, Queue: " + JmsMessageConfig.MY_SEND_RCV_QUEUE + ", " + LocalDateTime.now())
                .build();

        // Interface funcional de la que se envía el mensaje
        MessageCreator messageCreator = session -> {
            try {

                Message helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                helloMessage.setStringProperty("_type", "com.jesusfc.demo.model.JmsMessage");
                return helloMessage;

            } catch (JsonProcessingException e) {
                throw new JMSException(e.getMessage());
            }

        };

        Message IsMessageReceived = jmsTemplate.sendAndReceive(JmsMessageConfig.MY_SEND_RCV_QUEUE, messageCreator);

        System.out.println("Scheduled Message Sent!! and It has been RECEIVED!! -> " + IsMessageReceived.getBody(String.class));

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
