package com.jesusfc.demo.service;

import com.jesusfc.demo.config.FeignClientsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on abr - 2024
 */
@FeignClient(name = "feign-service", url = "http://localhost:8082/service_2", configuration = FeignClientsConfig.class)
public interface DemoServiceFeignClient {

    @GetMapping("/getFeign")
    ResponseEntity<String> getDemoFeignService1();

}
