package com.modsen.software.driver.kafka.event

data class DriverRatingRecalculated(val driverId: Long, val newValue: Float) :
    BaseRatingEvent(DriverRatingRecalculated::class.java.simpleName) {
        constructor(): this(0, 0f)
    }