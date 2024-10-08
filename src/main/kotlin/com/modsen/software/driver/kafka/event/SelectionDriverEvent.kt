package com.modsen.software.driver.kafka.event

data class SelectionDriverEvent(override val rideId: Long) : BaseRideEvent(rideId, SelectionDriverEvent::class.java.simpleName) {
    constructor() : this(0)
}