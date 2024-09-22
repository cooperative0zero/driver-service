package com.modsen.software.driver.entity

import com.modsen.software.driver.entity.enumeration.Gender
import jakarta.persistence.*

@Entity
@Table(name = "drivers")
data class Driver (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "d_id")
    var id: Long?,

    @field:Column(name = "d_fml_names", nullable = false)
    var fmlNames: String?,

    @field:Column(name = "d_email", nullable = false)
    var email: String?,

    @field:Column(name = "d_phone", nullable = false)
    var phone: String?,

    @field:Enumerated(EnumType.STRING)
    @field:Column(name = "d_gender", nullable = false)
    var gender: Gender?,

    @field:OneToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "d_car_id")
    var car: Car?,

    @field:Column(name = "d_deleted", nullable = false)
    var isDeleted: Boolean?
) {
    constructor() : this(null, null, null, null, null, null, null)
}