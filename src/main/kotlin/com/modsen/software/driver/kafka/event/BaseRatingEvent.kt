package com.modsen.software.driver.kafka.event;

import lombok.Data;

@Data
public abstract class BaseRatingEvent {
    protected String type;
}
