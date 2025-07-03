package com.jesusfc.demo.kafka.integration;

import com.jesusfc.demo.kafka.handler.OrderCreatedHandler;
import com.jesusfc.demo.kafka.message.OrderDispatched;
import com.jesusfc.demo.kafka.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on jul - 2025
 */
@Slf4j
@SpringBootTest(classes = {
        OrderDispatchIntegrationTest.class,
        OrderCreatedHandler.class,
        DispatchService.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
@EmbeddedKafka(controlledShutdown = true)
public class OrderDispatchIntegrationTest {

    private static final String MY_PRODUCER_TOPIC = "my.super.topic";
    private static final String MY_CONSUMER_TOPIC = "my.consumer.topic";
    private static final String DISPATCH_TRACKING_TOPIC = "my.dispatch.tracking.topic";

    public static class KafkaTestListener {
        // This class is used to listen to Kafka messages in the integration test.
        // It can be implemented to verify that messages are being sent and received correctly.
        AtomicInteger dispatchPreparingCounter = new AtomicInteger(0);
        AtomicInteger orderDispatchedCounter = new AtomicInteger(0);

        @KafkaListener(
                id = "dispatchPreparingListener", topics = DISPATCH_TRACKING_TOPIC,
                groupId = "KafkaIntegrationTest",
                containerFactory = "kafkaListenerContainerFactory"
        )
        public void receivedDispatchPreparing(@Payload OrderDispatched payload) {
            log.info("Dispatch preparing message received: {}", payload);
            dispatchPreparingCounter.incrementAndGet();
        }
    }

    @Test
    public void testOrderDispatchFlow() {
        // This test will run the application context and ensure that the OrderCreatedHandler
        // is correctly set up to listen to the Kafka topic and process messages.
        // The actual message sending and verification would be handled in a more complete test setup.
        log.info("Integration test for order dispatch started.");
    }
}
