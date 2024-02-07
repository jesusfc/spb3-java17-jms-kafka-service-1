package com.jesusfc.demo.controller;

import com.jesusfc.demo.message.JmsSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class JmsControllerTest {

    @Mock
    private JmsSender jmsSender;

    private JmsController jmsController;

    @BeforeEach
    void setUp() {
        jmsController = new JmsController(jmsSender);
    }

    @Test
    void shouldSendMessage() {
        // given
        final String message = "Hello World!";

        // when
        jmsController.sendMessage(message);

        // then
        verify(jmsSender).sendMessage(message);
    }

}