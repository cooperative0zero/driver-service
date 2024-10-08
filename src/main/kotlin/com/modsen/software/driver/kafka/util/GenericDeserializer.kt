package com.modsen.software.driver.kafka.util

import com.modsen.software.driver.kafka.configuration.KafkaTopics
import org.apache.kafka.common.header.Headers
import org.springframework.kafka.support.serializer.JsonDeserializer

class GenericDeserializer : JsonDeserializer<Any>() {
    override fun deserialize(topic: String, headers: Headers, data: ByteArray): Any {
        return when (topic) {
            KafkaTopics.RIDES_TOPIC -> {
                RideDeserializer().use { rideDeserializer ->
                    return rideDeserializer.deserialize(topic, data)
                }
            }

            KafkaTopics.RATING_TOPIC -> RatingDeserializer().use { ratingDeserializer ->
                return ratingDeserializer.deserialize(topic, data)
            }

            else -> {super.deserialize(topic, data)}
        }
    }
}