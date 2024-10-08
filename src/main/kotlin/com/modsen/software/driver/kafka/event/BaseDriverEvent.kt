package com.modsen.software.driver.kafka.event

abstract class BaseDriverEvent (
    open val driverId: Long,
    val type: String,
)