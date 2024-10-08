package com.modsen.software.driver.kafka.consumer;

import com.modsen.software.driver.kafka.configuration.KafkaTopics;
import com.modsen.software.driver.kafka.event.*;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideConsumer {

    private final KafkaTemplate<String, BaseDriverEvent> kafkaTemplate;

    @KafkaListener(topics = KafkaTopics.RIDES_TOPIC, groupId = "driverConsumerGroup")
    public void listenRides(BaseRideEvent rideEvent) {
        System.out.println(rideEvent);

        if (rideEvent instanceof SelectionDriverEvent) {
            kafkaTemplate.send(KafkaTopics.DRIVER_TOPIC, new DriverSelectedEvent(1L, 2L));
        }
    }
}
