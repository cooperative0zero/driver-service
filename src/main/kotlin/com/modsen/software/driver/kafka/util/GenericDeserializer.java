package com.modsen.software.driver.kafka.util;

import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.modsen.software.driver.kafka.configuration.KafkaTopics;

public class GenericDeserializer extends JsonDeserializer<Object> {

    public GenericDeserializer() {}

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data)
    {
        switch (topic)
        {
            case KafkaTopics.RIDES_TOPIC:
                try (RideDeserializer rideDeserializer = new RideDeserializer()) {
                    return rideDeserializer.deserialize(topic, data);
                }
            case KafkaTopics.RATING_TOPIC:
                try (RatingDeserializer ratingDeserializer = new RatingDeserializer()) {
                    return ratingDeserializer.deserialize(topic, data);
                }
        }
        return super.deserialize(topic, data);
    }
}
