package com.modsen.software.driver.mapper

import com.modsen.software.driver.dto.DriverRequest
import com.modsen.software.driver.entity.Car
import com.modsen.software.driver.entity.Driver
import com.modsen.software.driver.entity.enumeration.DriverStatus
import com.modsen.software.driver.entity.enumeration.Gender
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class DriverRequestToDriver: Converter<DriverRequest, Driver> {
    override fun convert(source: DriverRequest): Driver {
        val car = Car()
        car.id = source.carId ?: 0

        return Driver(
            source.id ?: 0,
            fullName = source.fullName ?: "",
            email = source.email ?: "",
            phone = source.phone ?: "",
            Gender.valueOf(source.gender?.uppercase() ?: "UNSPECIFIED"),
            if (car.id == 0L) null else car,
            0.0f,
            BigDecimal(0.0),
            DriverStatus.valueOf(source.status?.uppercase() ?: "UNDEFINED"),
            source.isDeleted ?: false
        )
    }
}