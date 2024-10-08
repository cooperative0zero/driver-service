package com.modsen.software.driver.kafka.util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.modsen.software.driver.kafka.event.BaseRideEvent
import com.modsen.software.driver.kafka.event.SelectionDriverEvent
import com.modsen.software.driver.kafka.event.StatusChangedEvent
import org.apache.kafka.common.serialization.Deserializer

class RideDeserializer : Deserializer<BaseRideEvent> {
    private val objectMapper = ObjectMapper()

    override fun deserialize(topic: String, data: ByteArray): BaseRideEvent {
        try {
            val map: Map<String, Any> =
                objectMapper.readValue(data, object : TypeReference<Map<String, Any>>() {})
            val type = map["type"] as String

            if ("SelectionDriverEvent".contentEquals(type)) {
                return objectMapper.readValue(data, SelectionDriverEvent::class.java)
            } else if ("StatusChangedEvent".contentEquals(type)) {
                return objectMapper.readValue(data, StatusChangedEvent::class.java)
            }
            throw IllegalArgumentException("Unknown type: $type")
        } catch (e: Exception) {
            throw RuntimeException("Error deserializing ", e)
        }
    }
}

