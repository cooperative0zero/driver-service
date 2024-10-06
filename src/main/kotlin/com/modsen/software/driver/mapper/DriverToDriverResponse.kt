package com.modsen.software.driver.mapper

import com.modsen.software.driver.dto.DriverResponse
import com.modsen.software.driver.entity.Driver
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class DriverToDriverResponse: Converter<Driver, DriverResponse> {
    override fun convert(source: Driver): DriverResponse {
        return DriverResponse(
            source.id,
            source.fullName,
            source.email,
            source.phone,
            source.gender.toString(),
            source.car?.id ?: 0,
            source.rating,
            source.balance,
            source.status.toString(),
            source.isDeleted
        )
    }
}