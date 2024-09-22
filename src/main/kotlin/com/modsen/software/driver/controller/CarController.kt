package com.modsen.software.driver.controller

import com.modsen.software.driver.dto.CarRequest
import com.modsen.software.driver.dto.CarResponse
import com.modsen.software.driver.service.CarService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cars")
class CarController(
    private val carService: CarService
) {

    @GetMapping
    fun getAll(
        @RequestParam(required = false, defaultValue = "0") @Min(0) pageNumber: Int,
        @RequestParam(required = false, defaultValue = "10") @Min(1) pageSize: Int,
        @RequestParam(required = false, defaultValue = "id") sortBy: String,
        @RequestParam(required = false, defaultValue = "desc") sortOrder: String
    ): ResponseEntity<List<CarResponse>> {
        val response: List<CarResponse> = carService.getAll(pageNumber, pageSize, sortBy, sortOrder)
        return ResponseEntity<List<CarResponse>>(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable @Min(1) id: Long): ResponseEntity<CarResponse> {
        val response: CarResponse = carService.getById(id)
        return ResponseEntity<CarResponse>(response, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun softDelete(@PathVariable @Min(1) id: Long): ResponseEntity<String> {
        carService.softDelete(id)
        return ResponseEntity("Deleted successfully", HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody @Valid car: CarRequest): ResponseEntity<CarResponse> {
        val response: CarResponse = carService.save(car)
        return ResponseEntity<CarResponse>(response, HttpStatus.CREATED)
    }

    @PutMapping
    fun update(@RequestBody @Valid car: CarRequest): ResponseEntity<CarResponse> {
        val response: CarResponse = carService.update(car)
        return ResponseEntity<CarResponse>(response, HttpStatus.OK)
    }
}