package com.jesusfc.demo.kafka.service;

import com.jesusfc.demo.kafka.message.OrderCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on jun - 2025
 */
@Slf4j
@Service
public class DispatchService {

    public void process(OrderCreated payload) {
        log.info("Processing payload: {}", payload);
    }
}
