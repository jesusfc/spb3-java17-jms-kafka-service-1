package com.jesusfc.demo.kafka.handler;

import com.jesusfc.demo.kafka.message.OrderCreated;
import com.jesusfc.demo.kafka.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on jun - 2025
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedHandler {

    private final DispatchService dispatchService;

    @KafkaListener(id ="orderConsumerClient", topics = "my.super.topic", groupId = "my.super.group")
    public void listen(OrderCreated payload) {
        log.info("Order created with ID: {}", payload);
        dispatchService.process(payload);
        // Add your business logic here
    }
}
