package com.modsen.software.driver.dto

import java.math.BigDecimal
import java.time.OffsetDateTime

class RideResponse (
    val id: Long,

    val driverId: Long,

    val passengerId: Long,

    val departureAddress: String,

    val destinationAddress: String,

    val rideStatus: String,

    val creationDate: OffsetDateTime,

    val completionDate: OffsetDateTime,

    val price: BigDecimal,
)