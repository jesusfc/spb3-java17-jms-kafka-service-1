package com.jesusfc.demo.message;

import com.jesusfc.demo.config.JmsMessageConfig;
import com.jesusfc.demo.model.JmsMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
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

    @Scheduled(fixedRate = 5000) // every 5 seconds
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

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void sendAndReceiveMessage() {

        System.out.println("Test Message Scheduled");

        JmsMessage message = JmsMessage
                .builder()
                .uuid(UUID.randomUUID())
                .message("Test Message Scheduled")
                .build();

        /*
        MessageCreator messageCreator = new MessageCreator<JmsMessage>() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                return null;
            }
        }

        jmsTemplate.sendAndReceive(JmsMessageConfig.MY_SEND_RCV_QUEUE, message);
*/
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
