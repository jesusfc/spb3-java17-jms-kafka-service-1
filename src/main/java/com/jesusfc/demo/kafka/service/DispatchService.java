package com.jesusfc.demo.kafka.service;

import com.jesusfc.demo.kafka.message.OrderCreated;
import com.jesusfc.demo.kafka.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on jun - 2025
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DispatchService {


    private static final String ORDER_DISPATCHED_TOPIC = "my.order.dispatched.topic";
    private final KafkaTemplate<String, Object> kafkaProducer;


    /**
     * This method processes an OrderCreated event and sends an OrderDispatched event to the Kafka topic.
     * It constructs an OrderDispatched message based on the information from the OrderCreated event.
     * CREAMOS UN SERVICIO QUE PROCESA EL EVENTO OrderCreated, APLICA LA LÓGICA DE NEGOCIO
     * Y ENVÍAMOS UN EVENTO AL TOPIC  "my.order.dispatched.topic" informando que el pedido ha sido despachado.
     * <p>
     * De esta forma, hacemos el envío de un mensaje a otro topic de Kafka, es decir, recibimos un mensaje de un topic
     * con el Listener y enviamos otro mensaje a otro topic con el KafkaProducer. Vemos lectura y envío de mensajes
     * en Kafka.
     */
    public void process(OrderCreated orderCreated) throws ExecutionException, InterruptedException {

        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .item(orderCreated.getItem() + " - dispatched")
                .build();
        log.info("Processing orderDispatched (send to another topic): {}", orderDispatched);
        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();
    }
}
