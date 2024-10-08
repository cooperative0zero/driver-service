package com.modsen.software.driver.kafka.event;

import lombok.Data;

@Data
public abstract class BaseRideEvent {
    protected Long rideId;
    protected String type;
}
