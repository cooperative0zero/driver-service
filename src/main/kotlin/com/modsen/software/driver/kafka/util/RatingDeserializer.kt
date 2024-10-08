package com.modsen.software.driver.kafka.util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.modsen.software.driver.kafka.event.BaseRatingEvent
import com.modsen.software.driver.kafka.event.DriverRatingRecalculated
import com.modsen.software.driver.kafka.event.PassengerRatingRecalculated
import org.apache.kafka.common.serialization.Deserializer

class RatingDeserializer : Deserializer<BaseRatingEvent> {
    private val objectMapper = ObjectMapper()

    override fun deserialize(topic: String, data: ByteArray): BaseRatingEvent {
        try {
            val map: Map<String, Any> =
                objectMapper.readValue(data, object : TypeReference<Map<String, Any>>() {})
            val type = map["type"] as String?

            if ("DriverRatingRecalculated".contentEquals(type)) {
                return objectMapper.readValue(data, DriverRatingRecalculated::class.java)
            } else if ("PassengerRatingRecalculated".contentEquals(type)) {
                return objectMapper.readValue(data, PassengerRatingRecalculated::class.java)
            }

            throw IllegalArgumentException("Unknown type: $type")
        } catch (e: Exception) {
            throw RuntimeException("Error deserializing ", e)
        }
    }
}