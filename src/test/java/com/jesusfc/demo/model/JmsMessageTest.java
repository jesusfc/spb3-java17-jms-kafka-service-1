package com.jesusfc.demo.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
public class JmsMessageTest {
    @Test
    void testJmsMessage() {
        // Arrange
        final UUID uuid = UUID.randomUUID();
        final String to = "to";
        final String message = "message";
        final String body = "body";

        final JmsMessage jmsMessage = JmsMessage.builder()
                .uuid(uuid)
                .to(to)
                .message(message)
                .body(body)
                .build();

        // Act and Assert
        assertNotNull(jmsMessage);
        assertEquals(uuid, jmsMessage.getUuid());
        assertEquals(to, jmsMessage.getTo());
        assertEquals(message, jmsMessage.getMessage());
        assertEquals(body, jmsMessage.getBody());
    }
}
