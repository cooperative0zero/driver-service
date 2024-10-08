package com.modsen.software.driver.kafka.event

data class PassengerRatingRecalculated(val passengerId: Long, val newValue: Float)
    : BaseRatingEvent(PassengerRatingRecalculated::class.java.simpleName) {
        constructor() : this(0, 0f)
    }