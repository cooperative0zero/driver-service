package com.modsen.software.driver.service.impl

import com.modsen.software.driver.dto.DriverRequest
import com.modsen.software.driver.dto.DriverResponse
import com.modsen.software.driver.entity.Driver
import com.modsen.software.driver.exception.DriverAlreadyExistsException
import com.modsen.software.driver.exception.DriverNotExistsException
import com.modsen.software.driver.service.DriverService
import com.modsen.software.driver.repository.DriverRepository
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DriverServiceImpl (
    private var driverRepository: DriverRepository,
    private var conversionService: ConversionService,
) : DriverService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): DriverResponse {
        val driver = driverRepository.findById(id)
            .orElseThrow { DriverNotExistsException("Driver with id = $id not exists") }
        return conversionService.convert(driver, DriverResponse::class.java)!!
    }

    @Transactional(readOnly = true)
    override fun getAll(pageNumber: Int, pageSize: Int, sortBy: String, sortOrder: String): List<DriverResponse> {
        return driverRepository.findAll(
            PageRequest.of(
                pageNumber, pageSize, Sort.by(
                    Sort.Direction.fromString(
                        sortOrder
                    ), sortBy
                )
            )
        )
            .content
            .asSequence()
            .map { o -> conversionService.convert(o, DriverResponse::class.java)!!}
            .toList()
    }

    @Transactional
    override fun save(request: DriverRequest): DriverResponse {
        var driver = conversionService.convert(request, Driver::class.java)!!

        if (driver.id != null && driverRepository.existsById(driver.id!!)) {
            throw DriverAlreadyExistsException("Driver with id = ${driver.id} already exists")
        } else {
            driver = driverRepository.save(driver)
        }

        return conversionService.convert(driver, DriverResponse::class.java)!!
    }

    @Transactional
    override fun softDelete(id: Long) {
        if (driverRepository.softDelete(id) == 0) {
            throw DriverNotExistsException("Driver with id = $id not exists")
        }
    }

    @Transactional
    override fun update(request: DriverRequest): DriverResponse {
        if (!driverRepository.existsById(request.id!!)) throw DriverNotExistsException("Driver with id = ${request.id} not exists")

        val updated = conversionService.convert(request, Driver::class.java)!!

        return conversionService.convert(driverRepository.save(updated), DriverResponse::class.java)!!
    }
}