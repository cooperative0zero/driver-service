package com.modsen.software.driver.dto

data class PassengerResponse (
    val id: Long,

    val fmlNames: String,

    val email: String,

    val phone: String,

    val isDeleted: Boolean
)