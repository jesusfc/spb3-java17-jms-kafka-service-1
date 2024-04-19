package com.jesusfc.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on abr - 2024
 */
@FeignClient(name = "feignService1", path = "localhost:8080/service_2")
public interface DemoServiceFeignClient {

    @GetMapping("/get")
    ResponseEntity<String> getDemoFeignService1();

}
