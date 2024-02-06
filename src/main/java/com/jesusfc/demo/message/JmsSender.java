package com.jesusfc.demo.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusfc.demo.config.JmsMessageConfig;
import com.jesusfc.demo.model.JmsMessage;
import lombok.AllArgsConstructor;
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
    private ObjectMapper objectMapper;

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void sendMessage() {
        try {
            System.out.println("Test Message Scheduled");

            JmsMessage message = JmsMessage
                    .builder()
                    .uuid(UUID.randomUUID())
                    .message("Test Message Scheduled")
                    .build();

            jmsTemplate.convertAndSend(JmsMessageConfig.MY_QUEUE, message);
            System.out.println("Scheduled Message Sent!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /*
    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void sendAndReplyToMeMessage() throws JMSException {

        System.out.println("Test Message Scheduled");

        JmsMessage message = JmsMessage
                .builder()
                .uuid(UUID.randomUUID())
                .to("jfcaraballo@gmail.com")
                .message("Test Message Scheduled")
                .body("reply_back_to_me")
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

        System.out.println("Scheduled Message Sent!! and It has been received!! + " + IsMessageReceived.getBody(String.class));

    }
*/
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
