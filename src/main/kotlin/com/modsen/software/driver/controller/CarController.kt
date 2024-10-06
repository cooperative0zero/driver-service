package com.modsen.software.driver.controller

import com.modsen.software.driver.dto.CarRequest
import com.modsen.software.driver.dto.CarResponse
import com.modsen.software.driver.dto.PaginatedResponse
import com.modsen.software.driver.entity.Car
import com.modsen.software.driver.service.CarServiceImpl
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/api/v1/cars")
class CarController(
    private val carService: CarServiceImpl,
    private val conversionService: ConversionService,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(
        @RequestParam(required = false, defaultValue = "0") @Min(0) pageNumber: Int,
        @RequestParam(required = false, defaultValue = "10") @Min(1) pageSize: Int,
        @RequestParam(required = false, defaultValue = "id") sortBy: String,
        @RequestParam(required = false, defaultValue = "desc") sortOrder: String
    ): PaginatedResponse<CarResponse> {
        val result = carService.getAll(pageNumber, pageSize, sortBy, sortOrder)
            .asSequence()
            .map { o -> conversionService.convert(o, CarResponse::class.java)!!}
            .toList()

        return PaginatedResponse(result, pageNumber, pageSize, result.size)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable @Min(1) id: Long) = conversionService.convert(carService.getById(id), CarResponse::class.java)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun softDelete(@PathVariable @Min(1) id: Long)  = carService.softDelete(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody @Valid car: CarRequest) = conversionService
        .convert(carService.save(conversionService.convert(car, Car::class.java)!!), CarResponse::class.java)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody @Valid car: CarRequest) = conversionService
        .convert(carService.update(conversionService.convert(car, Car::class.java)!!), CarResponse::class.java)
}