package com.modsen.software.driver.controller

import com.modsen.software.driver.dto.DriverRequest
import com.modsen.software.driver.dto.DriverResponse
import com.modsen.software.driver.service.DriverService
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
@RequestMapping("/api/v1/drivers")
class DriverController(
    private val driverService: DriverService
) {

    @GetMapping
    fun getAll(
        @RequestParam(required = false, defaultValue = "0") @Min(0) pageNumber: Int,
        @RequestParam(required = false, defaultValue = "10") @Min(1) pageSize: Int,
        @RequestParam(required = false, defaultValue = "id") sortBy: String,
        @RequestParam(required = false, defaultValue = "desc") sortOrder: String
    ): ResponseEntity<List<DriverResponse>> {
        val response: List<DriverResponse> = driverService.getAll(pageNumber, pageSize, sortBy, sortOrder)
        return ResponseEntity<List<DriverResponse>>(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable @Min(1) id: Long): ResponseEntity<DriverResponse> {
        val response: DriverResponse = driverService.getById(id)
        return ResponseEntity<DriverResponse>(response, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun softDelete(@PathVariable @Min(1) id: Long): ResponseEntity<String> {
        driverService.softDelete(id)
        return ResponseEntity("Deleted successfully", HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody @Valid driver: DriverRequest): ResponseEntity<DriverResponse> {
        val response: DriverResponse = driverService.save(driver)
        return ResponseEntity<DriverResponse>(response, HttpStatus.CREATED)
    }

    @PutMapping
    fun update(@RequestBody @Valid driver: DriverRequest): ResponseEntity<DriverResponse> {
        val response: DriverResponse = driverService.update(driver)
        return ResponseEntity<DriverResponse>(response, HttpStatus.OK)
    }
}