package com.jesusfc.demo.controller;

import com.jesusfc.demo.service.DemoServiceFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author Jesús Fdez. Caraballo
 * Created on abr - 2024
 */
@AllArgsConstructor
@RequestMapping("/service_1")
@RestController
public class Service1Controller {

    private final DemoServiceFeignClient demoServiceFeignClient;

    @GetMapping("/get")
    public ResponseEntity<String> getService1Get1() {
        return new ResponseEntity<>("El servicio 1 - Get 1 - responde OK", HttpStatus.OK);
    }

    @GetMapping("/get2")
    public ResponseEntity<String> getService1Get2() {
        return new ResponseEntity<>("El servicio 1 - Get 2 - responde OK", HttpStatus.OK);
    }

    @GetMapping("/get3")
    public ResponseEntity<String> getService1GetFeignService() {
        System.out.println("Llamada un servicio externo con Feign");
        return demoServiceFeignClient.getDemoFeignService1();
    }


}
