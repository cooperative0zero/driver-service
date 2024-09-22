package com.modsen.software.driver.service.impl

import com.modsen.software.driver.dto.CarRequest
import com.modsen.software.driver.dto.CarResponse
import com.modsen.software.driver.entity.Car
import com.modsen.software.driver.exception.CarAlreadyExistsException
import com.modsen.software.driver.exception.CarNotExistsException
import com.modsen.software.driver.repository.CarRepository
import com.modsen.software.driver.service.CarService
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarServiceImpl (
    private var carRepository: CarRepository,
    private var conversionService: ConversionService,
) : CarService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): CarResponse {
        val car = carRepository.findById(id)
            .orElseThrow { CarNotExistsException("Car with id = $id not exists") }
        return conversionService.convert(car, CarResponse::class.java)!!
    }

    @Transactional(readOnly = true)
    override fun getAll(pageNumber: Int, pageSize: Int, sortBy: String, sortOrder: String): List<CarResponse> {
        return carRepository.findAll(
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
            .map { o -> conversionService.convert(o, CarResponse::class.java)!!}
            .toList()
    }

    @Transactional
    override fun save(request: CarRequest): CarResponse {
        var car = conversionService.convert(request, Car::class.java)!!

        if (car.id != null && carRepository.existsById(car.id!!)) {
            throw CarAlreadyExistsException("Car with id = ${car.id} already exists")
        } else {
            car = carRepository.save(car)
        }

        return conversionService.convert(car, CarResponse::class.java)!!
    }

    @Transactional
    override fun softDelete(id: Long) {
        if (carRepository.softDelete(id) == 0) {
            throw CarNotExistsException("Car with id = $id not exists")
        }
    }

    @Transactional
    override fun update(request: CarRequest): CarResponse {
        if (!carRepository.existsById(request.id!!)) throw CarNotExistsException("Car with id = ${request.id} not exists")

        val updated = conversionService.convert(request, Car::class.java)!!

        return conversionService.convert(carRepository.save(updated), CarResponse::class.java)!!
    }
}