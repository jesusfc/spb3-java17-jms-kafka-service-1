package com.jesusfc.demo.message;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
@SpringBootTest
class JmsReceiverListenerTest {

    @Autowired
    private JmsReceiverListener jmsReceiverListener;

    @Test
    void receiveMessage() {

    }
}