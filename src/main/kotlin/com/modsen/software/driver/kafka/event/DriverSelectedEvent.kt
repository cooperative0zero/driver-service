package com.modsen.software.driver.kafka.event

data class DriverSelectedEvent(override var driverId: Long, var rideId: Long) :
    BaseDriverEvent(driverId, DriverSelectedEvent::class.java.simpleName) {
        constructor() : this(0, 0)
    }