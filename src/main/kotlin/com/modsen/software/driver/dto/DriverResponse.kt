package com.modsen.software.driver.dto

data class DriverResponse(
    val id: Long?,
    val fmlNames: String?,
    val email: String?,
    val phone: String?,
    val gender: String?,
    val carId: Long?,
    val isDeleted: Boolean?
)
