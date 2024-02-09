package com.jesusfc.demo.message;

import com.jesusfc.demo.config.JmsMessageConfig;
import com.jesusfc.demo.model.JmsMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
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

    private final JmsMessageConfig artemisConfig;

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void sendMessage() {
        try {

            JmsTemplate jmsTemplate = artemisConfig.artemisJmsTemplate();
            JmsMessage message = JmsMessage
                    .builder()
                    .uuid(UUID.randomUUID())
                    .to("jfcaraballo@gmail.com")
                    .message("Service 1- Message Scheduled convert and send, Queue: " + JmsMessageConfig.MY_QUEUE + ", " + LocalDateTime.now())
                    .body("Service 1 - Body Test 1 Message Scheduled convert and send, Queue: " + JmsMessageConfig.MY_QUEUE + ", " + LocalDateTime.now())
                    .build();

            jmsTemplate.convertAndSend(JmsMessageConfig.MY_QUEUE, message);
            System.out.println("Service 1 - Message 1 - Scheduled Message Sent!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void sendAndReplyToMeMessage() throws JMSException {

        System.out.println("Service 1 - Message 2 - Scheduled Message Sent!");
        JmsTemplate jmsTemplate = artemisConfig.artemisJmsTemplate();

        JmsMessage jmsMessage = JmsMessage
                .builder()
                .uuid(UUID.randomUUID())
                .to("jfcaraballo@gmail.com")
                .message("Service 1 - Message 2 Scheduled send and received, Queue: " + JmsMessageConfig.MY_SEND_RCV_QUEUE + ", " + LocalDateTime.now())
                .body("Service 1 - Body Test 2 Message Scheduled convert and send, Queue: " + JmsMessageConfig.MY_SEND_RCV_QUEUE + ", " + LocalDateTime.now())
                .build();

        Message IsMessageReceived = jmsTemplate.sendAndReceive(JmsMessageConfig.MY_SEND_RCV_QUEUE, session -> {
            Message helloMessage = session.createObjectMessage(jmsMessage);
            helloMessage.setJMSPriority(1);
            return helloMessage;
        });

        if (IsMessageReceived != null) {
            System.out.println("Service 1 - Scheduled Message Sent!! and It has been RECEIVED!! -> " + IsMessageReceived.getJMSMessageID());
        } else {
            System.out.println("Message NULL Received!");
        }

    }

    public void sendMessage(String message) throws JMSException {
        JmsTemplate jmsTemplate = artemisConfig.artemisJmsTemplate();
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
