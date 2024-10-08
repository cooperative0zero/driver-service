package com.modsen.software.driver.kafka.event

abstract class BaseRideEvent (
    open val rideId: Long,
    val type: String,
)