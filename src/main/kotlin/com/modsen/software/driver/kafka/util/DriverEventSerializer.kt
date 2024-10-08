package com.modsen.software.driver.kafka.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.modsen.software.driver.kafka.event.BaseDriverEvent
import com.modsen.software.driver.kafka.event.DriverSelectedEvent
import org.apache.kafka.common.serialization.Serializer

class DriverEventSerializer : Serializer<BaseDriverEvent> {
    private val objectMapper = ObjectMapper()

    override fun serialize(s: String, baseDriverEvent: BaseDriverEvent): ByteArray {
        try {
            if (baseDriverEvent is DriverSelectedEvent) {
                return objectMapper.writeValueAsBytes(baseDriverEvent)
            }

            throw IllegalArgumentException("Unknown type: " + baseDriverEvent::class)
        } catch (e: Exception) {
            throw RuntimeException("Error serializing MyCustomObject", e)
        }
    }
}