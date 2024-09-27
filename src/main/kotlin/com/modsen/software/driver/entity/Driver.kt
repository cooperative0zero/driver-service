package com.modsen.software.driver.entity

import com.modsen.software.driver.entity.enumeration.DriverStatus
import com.modsen.software.driver.entity.enumeration.Gender
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "drivers")
data class Driver (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "d_id")
    var id: Long = 0,

    @field:Column(name = "d_full_name", nullable = false)
    var fullName: String = "",

    @field:Column(name = "d_email", nullable = false)
    var email: String = "",

    @field:Column(name = "d_phone", nullable = false)
    var phone: String = "",

    @field:Enumerated(EnumType.STRING)
    @field:Column(name = "d_gender", nullable = false)
    var gender: Gender = Gender.UNSPECIFIED,

    @field:OneToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "d_car_id")
    var car: Car? = null,

    @field:Column(name = "d_rating", nullable = false)
    var rating: Float = 0.0f,

    @field:Column(name = "d_balance", nullable = false)
    var balance: BigDecimal = BigDecimal(0),

    @field:Column(name = "d_status", nullable = false)
    var status: DriverStatus = DriverStatus.UNDEFINED,

    @field:Column(name = "d_deleted", nullable = false)
    var isDeleted: Boolean = false
)