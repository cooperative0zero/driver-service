package com.modsen.software.driver.controller

import com.modsen.software.driver.dto.DriverRequest
import com.modsen.software.driver.dto.DriverResponse
import com.modsen.software.driver.dto.PaginatedResponse
import com.modsen.software.driver.entity.Driver
import com.modsen.software.driver.service.DriverServiceImpl
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/api/v1/drivers")
class DriverController(
    private val driverService: DriverServiceImpl,
    private val conversionService: ConversionService,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(
        @RequestParam(required = false, defaultValue = "0") @Min(0) pageNumber: Int,
        @RequestParam(required = false, defaultValue = "10") @Min(1) pageSize: Int,
        @RequestParam(required = false, defaultValue = "id") sortBy: String,
        @RequestParam(required = false, defaultValue = "desc") sortOrder: String
    ): PaginatedResponse<DriverResponse> {
        val result = driverService.getAll(pageNumber, pageSize, sortBy, sortOrder)
            .asSequence()
            .map { o -> conversionService.convert(o, DriverResponse::class.java)!!}
            .toList()

        return PaginatedResponse(result, pageNumber, pageSize, result.size)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable @Min(1) id: Long) = conversionService
        .convert(driverService.getById(id), DriverResponse::class.java)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun softDelete(@PathVariable @Min(1) id: Long) = driverService.softDelete(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody @Valid driverRequest: DriverRequest) = conversionService
        .convert(driverService.save(conversionService.convert(driverRequest, Driver::class.java)!!), DriverResponse::class.java)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody @Valid driverRequest: DriverRequest) =
        conversionService.convert(driverService.update(conversionService.convert(driverRequest, Driver::class.java)!!),
            DriverResponse::class.java)
}