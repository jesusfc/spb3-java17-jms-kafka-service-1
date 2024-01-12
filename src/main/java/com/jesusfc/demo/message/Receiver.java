package com.jesusfc.demo.message;

import com.jesusfc.demo.model.JmsMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(JmsMessage message) {
        System.out.println("Received <" + message + ">");
    }

}
