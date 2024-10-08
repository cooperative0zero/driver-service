package com.modsen.software.driver.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CarRequest(
    @field:Min(1)
    val id: Long?,

    @field:NotBlank
    val color: String,

    @field:NotBlank
    val model: String,

    @field:NotBlank
    val vehicleNumber: String,

    @field:Min(1)
    val driverId: Long?,

    @field:NotNull
    val isDeleted: Boolean,
)
