package com.jesusfc.demo.kafka.service;

import com.jesusfc.demo.kafka.message.OrderCreated;
import com.jesusfc.demo.kafka.message.OrderDispatched;
import com.jesusfc.demo.kafka.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on jun - 2025
 */
class DispatchServiceTest {

    private DispatchService dispatchService;
    private KafkaTemplate kafkaTemplateMock;

    @BeforeEach
    void setUp() {
        kafkaTemplateMock = mock(KafkaTemplate.class);
        dispatchService = new DispatchService(kafkaTemplateMock);
    }

    @Test
    void process() throws Exception {
        when(kafkaTemplateMock.send(anyString(), any(OrderDispatched.class))).thenReturn(mock(CompletableFuture.class)); // Mock the send method

        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(UUID.randomUUID(), UUID.randomUUID().toString());
        dispatchService.process(testEvent);
        verify(kafkaTemplateMock, times(1)).send(eq("my.order.dispatched.topic"), any(OrderDispatched.class));
    }

    @Test
    void process_ProducerThrowsException() {

        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(UUID.randomUUID(), UUID.randomUUID().toString());
        doThrow(new RuntimeException("Kafka producer error")).when(kafkaTemplateMock).send(eq("my.order.dispatched.topic"), any(OrderDispatched.class));

        Exception exception = assertThrows(RuntimeException.class, () -> dispatchService.process(testEvent));

        verify(kafkaTemplateMock, times(1)).send(eq("my.order.dispatched.topic"), any(OrderDispatched.class));
        assertThat(exception.getMessage()).contains("Kafka producer error");


    }
}