package com.modsen.software.driver.service

import com.modsen.software.driver.dto.CarRequest
import com.modsen.software.driver.dto.CarResponse

interface CarService {
    fun getById(id: Long): CarResponse

    fun getAll(pageNumber: Int, pageSize: Int, sortBy: String, sortOrder: String): List<CarResponse>

    fun save(request: CarRequest): CarResponse

    fun softDelete(id: Long)

    fun update(request: CarRequest): CarResponse
}