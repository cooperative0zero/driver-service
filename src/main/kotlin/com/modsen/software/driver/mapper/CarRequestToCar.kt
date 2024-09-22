package com.modsen.software.driver.mapper

import com.modsen.software.driver.dto.CarRequest
import com.modsen.software.driver.entity.Car
import com.modsen.software.driver.entity.Driver
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CarRequestToCar: Converter<CarRequest, Car> {
    override fun convert(source: CarRequest): Car? {
        val driver = Driver()
        driver.id = source.driverId

        return Car(
            source.id,
            source.color!!,
            source.model!!,
            source.vehicleNumber!!,
            if (source.driverId == null) null else driver,
            source.isDeleted!!
        )
    }
}