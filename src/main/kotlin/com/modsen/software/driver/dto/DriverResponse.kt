package com.modsen.software.driver.dto

import java.math.BigDecimal

data class DriverResponse(
    val id: Long,
    val fullName: String,
    val email: String,
    val phone: String,
    val gender: String,
    val carId: Long,
    val rating: Float,
    val balance: BigDecimal,
    val status: String,
    val isDeleted: Boolean
)
