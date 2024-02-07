package com.jesusfc.demo.message;

import com.jesusfc.demo.config.JmsMessageConfig;
import com.jesusfc.demo.model.JmsMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
@Component
public class JmsReceiverListener {

    //@JmsListener(destination = JmsMessageConfig.MY_QUEUE)
    public void receiveMessage(@Payload JmsMessage helloWorldMessage,
                               @Headers MessageHeaders headers, Message message) {

        System.out.println("I Got a Message!!!!!");
        System.out.println(helloWorldMessage);
        System.out.println("Received <" + message + ">");
    }

    @JmsListener(destination = JmsMessageConfig.MY_SEND_RCV_QUEUE)
    public void receiveHelloMessage(@Payload JmsMessage helloWorldMessage,
                                    @Headers MessageHeaders headers, Message message) {

        System.out.println("I Got a Hello World Message!!!!!");
        System.out.println(helloWorldMessage);
        System.out.println("Received Hello world <" + message + ">");
    }

}
