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
        driver.id = source.driverId ?: 0

        return Car(
            source.id ?: 0,
            color = source.color ?: "",
            model = source.model ?: "",
            vehicleNumber = source.vehicleNumber ?: "",
            if (driver.id == 0L) null else driver,
            source.isDeleted ?: false
        )
    }
}