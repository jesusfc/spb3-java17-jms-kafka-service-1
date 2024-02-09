package com.jesusfc.demo.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Author Jesús Fdez. Caraballo
 * Created on dic - 2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "_type")
public class JmsMessage implements Serializable {
    private UUID uuid;
    private String to;
    private String message;
    private String body;
}
