package com.modsen.software.driver.service

import com.modsen.software.driver.dto.CarRequest
import com.modsen.software.driver.entity.Car
import com.modsen.software.driver.exception.CarAlreadyExistsException
import com.modsen.software.driver.exception.CarNotExistsException
import com.modsen.software.driver.repository.CarRepository
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarServiceImpl (
    private var carRepository: CarRepository,
    private var conversionService: ConversionService,
) {

    @Transactional(readOnly = true)
    fun getById(id: Long): Car = carRepository.findById(id)
            .orElseThrow { CarNotExistsException("Car with id = $id not exists") }

    @Transactional(readOnly = true)
    fun getAll(pageNumber: Int, pageSize: Int, sortBy: String, sortOrder: String): List<Car> =
        carRepository.findAll(
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
    fun save(request: Car): Car {
        var car = request
        if (carRepository.existsById(car.id)) {
            throw CarAlreadyExistsException("Car with id = ${car.id} already exists")
        } else {
            car = carRepository.save(car)
        }

        return conversionService.convert(car, Car::class.java)!!
    }

    @Transactional
    fun softDelete(id: Long) {
        if (carRepository.softDelete(id) == 0) {
            throw CarNotExistsException("Car with id = $id not exists")
        }
    }

    @Transactional
    fun update(request: Car): Car {
        if (!carRepository.existsById(request.id)) throw CarNotExistsException("Car with id = ${request.id} not exists")

        return carRepository.save(request)
    }
}