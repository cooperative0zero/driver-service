package com.modsen.software.driver.kafka.consumer

import com.modsen.software.driver.kafka.configuration.KafkaTopics
import com.modsen.software.driver.kafka.event.BaseDriverEvent
import com.modsen.software.driver.kafka.event.BaseRideEvent
import com.modsen.software.driver.kafka.event.DriverSelectedEvent
import com.modsen.software.driver.kafka.event.SelectionDriverEvent
import lombok.RequiredArgsConstructor
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class RideConsumer(private val kafkaTemplate: KafkaTemplate<String, BaseDriverEvent>) {

    @KafkaListener(topics = [KafkaTopics.RIDES_TOPIC], groupId = "driverConsumerGroup")
    fun listenRides(rideEvent: BaseRideEvent) {
        println(rideEvent)

        if (rideEvent is SelectionDriverEvent) {
            kafkaTemplate.send(KafkaTopics.DRIVER_TOPIC, DriverSelectedEvent(1L, 2L))
        }
    }
}