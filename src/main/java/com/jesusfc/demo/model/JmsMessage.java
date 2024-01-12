package com.jesusfc.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Author Jesús Fdez. Caraballo
 * Created on dic - 2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JmsMessage {

    private UUID uuid;
    private String to;
    private String message;
    private String body;

}
