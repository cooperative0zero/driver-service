package com.modsen.software.driver.kafka.consumer

import com.modsen.software.driver.kafka.configuration.KafkaTopics
import com.modsen.software.driver.kafka.event.BaseRatingEvent
import lombok.RequiredArgsConstructor
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class RatingConsumer {

    @KafkaListener(topics = [KafkaTopics.RATING_TOPIC], groupId = "driverConsumerGroup")
    fun listenDrivers(driverEvent: BaseRatingEvent) {
        println(driverEvent)
    }
}
