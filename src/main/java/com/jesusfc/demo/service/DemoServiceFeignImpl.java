package com.jesusfc.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Author Jesús Fdez. Caraballo
 * jesus.fdez.caraballo@gmail.com
 * Created on abr - 2024
 */
public class DemoServiceFeignImpl implements DemoServiceFeignClient{
    @Override
    public ResponseEntity<String> getDemoFeignService1() {
        return new ResponseEntity<>("Response Demo Feign Service 1", HttpStatus.OK);
    }
}
