package com.jesusfc.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on abr - 2024
 */
@FeignClient(name = "demoService1")
public interface DemoServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/feign_service_1/get")
    ResponseEntity<String> getDemoFeignService1();
}
