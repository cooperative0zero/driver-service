package com.modsen.software.driver.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import java.math.BigDecimal

data class DriverRequest(
    @field:Min(1)
    val id: Long?,

    @field:NotBlank
    val fullName: String,

    @field:NotNull
    @field:Email
    val email: String,

    @field:NotNull
    @field:Pattern(regexp = "^\\d{6,}$", message = "Phone number must be valid")
    val phone: String,

    @field:NotBlank
    val gender: String,

    @field:Min(1)
    val carId: Long?,

    val status: String?,

    @field:NotNull
    val isDeleted: Boolean
)
