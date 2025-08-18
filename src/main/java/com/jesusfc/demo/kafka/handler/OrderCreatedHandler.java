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

    /*
     * This method listens to the "my.order.created.topic" Kafka topic for messages of type OrderCreated.
     * It processes the incoming OrderCreated message by calling the DispatchService.
     *
     * CREAMOS UN CONSUMIDOR DE KAFKA QUE ESCUCHA EL TEMA "my.order.created.topic"
     * La anotación @KafkaListener indica que este método es un consumidor de Kafka.
     * El parámetro "id" es un identificador único para este consumidor.
     * El parámetro "topics" especifica el tema de Kafka al que se suscribe.
     * El parámetro "groupId" define el grupo de consumidores al que pertenece este consumidor
     */
    @KafkaListener(
            id = "orderConsumerClient",
            topics = "my.order.created.topic",
            groupId = "my.super.group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(OrderCreated payload) {
        log.info("Order created with ID: {}", payload);
        try {
            // Envía el mensaje a DispatchService para su procesamiento
            log.info("Processing order created event: {}", payload);
            dispatchService.process(payload);
        } catch (Exception e) {
            log.error("Error processing order created event: {}", payload, e);
        }
    }
}
