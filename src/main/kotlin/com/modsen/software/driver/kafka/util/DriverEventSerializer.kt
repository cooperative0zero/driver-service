package com.modsen.software.driver.kafka.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modsen.software.driver.kafka.event.BaseDriverEvent;
import com.modsen.software.driver.kafka.event.DriverSelectedEvent;
import org.apache.kafka.common.serialization.Serializer;

public class DriverEventSerializer implements Serializer<BaseDriverEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, BaseDriverEvent baseDriverEvent) {
        try {
            if (baseDriverEvent instanceof DriverSelectedEvent) {
                return objectMapper.writeValueAsBytes(baseDriverEvent);
            }

            throw new IllegalArgumentException("Unknown type: " + baseDriverEvent.getClass());
        } catch (Exception e) {
            throw new RuntimeException("Error serializing MyCustomObject", e);
        }
    }
}
