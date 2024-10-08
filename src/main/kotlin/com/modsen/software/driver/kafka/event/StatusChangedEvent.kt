package com.modsen.software.driver.kafka.event

data class StatusChangedEvent(override val rideId: Long, var fromStatus: String, var toStatus: String, var userType: String) :
    BaseRideEvent(rideId, StatusChangedEvent::class.java.simpleName) {
        constructor() : this(0, "", "", "")
    }