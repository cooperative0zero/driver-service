package com.modsen.software.driver.entity

import jakarta.persistence.*

@Entity
@Table(name = "cars")
data class Car (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "c_id")
    var id: Long?,

    @field:Column(name = "c_color", nullable = false)
    var color: String?,

    @field:Column(name = "c_model", nullable = false)
    var model: String?,

    @field:Column(name = "c_vehicle_number")
    var vehicleNumber: String?,

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY)
    var driver: Driver?,

    @field:Column(name = "c_deleted", nullable = false)
    var isDeleted: Boolean?,
) {
    constructor() : this(null ,null ,null, null, null, null)
}