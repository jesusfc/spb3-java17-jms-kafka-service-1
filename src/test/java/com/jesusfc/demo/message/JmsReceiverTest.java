package com.jesusfc.demo.message;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
@SpringBootTest
@ActiveProfiles("test")
class JmsReceiverTest {

    @Autowired
    private JmsReceiver jmsReceiver;

    @Test
    void receiveMessage() {

    }
}