package com.modsen.software.driver.dto

data class CarResponse(
    val id: Long?,
    val color: String?,
    val model: String?,
    val vehicleNumber: String?,
    val driverId: Long?,
    val isDeleted: Boolean?
)
