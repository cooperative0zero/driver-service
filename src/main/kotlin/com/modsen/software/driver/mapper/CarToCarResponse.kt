package com.modsen.software.driver.mapper

import com.modsen.software.driver.dto.CarResponse
import com.modsen.software.driver.entity.Car
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CarToCarResponse: Converter<Car, CarResponse> {
    override fun convert(source: Car): CarResponse {
        return CarResponse(
            source.id!!,
            source.color!!,
            source.model!!,
            source.vehicleNumber!!,
            source.driver?.id,
            source.isDeleted!!
        )
    }
}