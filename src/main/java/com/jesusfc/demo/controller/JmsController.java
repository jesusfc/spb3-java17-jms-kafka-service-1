package com.jesusfc.demo.controller;

import com.jesusfc.demo.message.JmsSender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
@AllArgsConstructor
@RestController
public class JmsController {

    private JmsSender jmsSender;

    /**
     * Sends a message to the JMS queue.
     *
     * @param message the message to be sent
     */
    @PostMapping("/send")
    public void sendMessage(@RequestBody String message) {
        jmsSender.sendMessage(message);
    }

}
