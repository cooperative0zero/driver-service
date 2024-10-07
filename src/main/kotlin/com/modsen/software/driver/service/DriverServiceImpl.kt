package com.modsen.software.driver.service

import com.modsen.software.driver.entity.Driver
import com.modsen.software.driver.exception.DriverAlreadyExistsException
import com.modsen.software.driver.exception.DriverNotExistsException
import com.modsen.software.driver.repository.DriverRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DriverServiceImpl (
    private var driverRepository: DriverRepository,
) {

    @Transactional(readOnly = true)
    fun getById(id: Long): Driver = driverRepository.findById(id)
        .orElseThrow { DriverNotExistsException("Driver with id = $id not exists") }

    @Transactional(readOnly = true)
    fun getAll(pageNumber: Int, pageSize: Int, sortBy: String, sortOrder: String): List<Driver> =
        driverRepository.findAll(
            PageRequest.of(
                pageNumber, pageSize, Sort.by(
                    Sort.Direction.fromString(
                        sortOrder
                    ), sortBy
                )
            )
        )
            .content

    @Transactional
    fun save(driver: Driver): Driver {
        var toSave = driver
        if (driverRepository.existsById(toSave.id)) {
            throw DriverAlreadyExistsException("Driver with id = ${driver.id} already exists")
        } else {
            toSave = driverRepository.save(driver)
        }

        return toSave
    }

    @Transactional
    fun softDelete(id: Long) {
        if (driverRepository.softDelete(id) == 0) {
            throw DriverNotExistsException("Driver with id = $id not exists")
        }
    }

    @Transactional
    fun update(driver: Driver): Driver {
        val driverFromDb: Driver = driverRepository.findById(driver.id).orElseThrow { throw DriverNotExistsException("Driver with id = ${driver.id} not exists") }

        driver.balance = driverFromDb.balance
        driver.rating = driverFromDb.rating

        return driverRepository.save(driver)
    }
}