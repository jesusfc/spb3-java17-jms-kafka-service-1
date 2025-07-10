package com.jesusfc.demo.kafka.integration;

import com.jesusfc.demo.config.KafkaConfig;
import com.jesusfc.demo.kafka.message.OrderCreated;
import com.jesusfc.demo.kafka.message.OrderDispatched;
import com.jesusfc.demo.kafka.util.TestEventData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.UUID.randomUUID;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on jul - 2025
 */
@Slf4j
@SpringBootTest(classes = {KafkaConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Import(OrderDispatchIntegrationTest.TestConfig.class)
@ActiveProfiles("test")
@EmbeddedKafka(controlledShutdown = true)
public class OrderDispatchIntegrationTest {

    private static final String MY_PRODUCER_TOPIC = "my.super.topic";
    private static final String MY_CONSUMER_TOPIC = "my.consumer.topic";
    private static final String DISPATCH_TRACKING_TOPIC = "my.dispatch.tracking.topic";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaTestListener testListener;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Configuration
    static class TestConfig {
        @Bean
        public KafkaTestListener testListener() {
            return new KafkaTestListener();
        }
    }


    public static class KafkaTestListener {
        // This class is used to listen to Kafka messages in the integration test.
        // It can be implemented to verify that messages are being sent and received correctly.
        AtomicInteger dispatchPreparingCounter = new AtomicInteger(0);
        AtomicInteger orderDispatchedCounter = new AtomicInteger(0);

        @KafkaListener(groupId = "KafkaIntegrationTest", topics = DISPATCH_TRACKING_TOPIC)
        public void receivedDispatchPreparing(@Payload OrderDispatched payload) {
            log.info("Dispatch preparing message received: {}", payload);
            dispatchPreparingCounter.incrementAndGet();
        }

        @KafkaListener(groupId = "KafkaIntegrationTest", topics = MY_CONSUMER_TOPIC)
        public void receivedOrderDispatched(@Payload OrderDispatched payload) {
            log.info("Order dispatched message received: {}", payload);
            orderDispatchedCounter.incrementAndGet();
        }
    }

    @BeforeEach
    public void setUp() {
        testListener.dispatchPreparingCounter.set(0);
        testListener.orderDispatchedCounter.set(0);

        registry.getListenerContainers().forEach(container -> {
            ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
        });

    }

    @Test
    public void testOrderDispatchFlow() throws ExecutionException, InterruptedException {

        OrderCreated orderDispatched = TestEventData.buildOrderCreatedEvent(randomUUID(), "tracking123");
        sendMessageOrderDispatched(MY_PRODUCER_TOPIC, orderDispatched);

        await().atMost(3, TimeUnit.SECONDS).pollDelay(100, TimeUnit.MILLISECONDS)
                .until(testListener.dispatchPreparingCounter::get, equalTo(1));

        await().atMost(1, TimeUnit.SECONDS).pollDelay(100, TimeUnit.MILLISECONDS)
                .until(testListener.orderDispatchedCounter::get, equalTo(1));

    }

    private void sendMessageOrderDispatched(String topic, OrderCreated orderCreated) throws ExecutionException, InterruptedException {
        log.info("Sending OrderCreated event: {}", orderCreated);
        kafkaTemplate.send(MessageBuilder
                .withPayload(orderCreated)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build()).get();
        log.info("OrderCreated event sent successfully");
    }
}
