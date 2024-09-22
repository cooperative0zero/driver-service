package com.modsen.software.driver.mapper

import com.modsen.software.driver.dto.DriverRequest
import com.modsen.software.driver.entity.Car
import com.modsen.software.driver.entity.Driver
import com.modsen.software.driver.entity.enumeration.Gender
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class DriverRequestToDriver: Converter<DriverRequest, Driver> {
    override fun convert(source: DriverRequest): Driver {
        val car = Car()
        car.id = source.carId

        return Driver(
            source.id,
            source.fmlNames!!,
            source.email!!,
            source.phone!!,
            Gender.valueOf(source.gender!!.uppercase()),
            if (car.id == null) null else car,
            source.isDeleted!!
        )
    }
}