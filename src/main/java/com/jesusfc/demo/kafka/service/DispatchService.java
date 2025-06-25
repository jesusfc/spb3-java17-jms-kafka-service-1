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

    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";
    private final KafkaTemplate<String, Object> kafkaProducer;


    public void process(OrderCreated orderCreated) throws ExecutionException, InterruptedException {
        log.info("Processing orderCreated: {}", orderCreated);
        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .item(orderCreated.getItem())
                .build();
        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();
    }
}
