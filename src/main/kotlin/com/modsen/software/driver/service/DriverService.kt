package com.modsen.software.driver.service

import com.modsen.software.driver.dto.DriverRequest
import com.modsen.software.driver.dto.DriverResponse

interface DriverService {
    fun getById(id: Long): DriverResponse

    fun getAll(pageNumber: Int, pageSize: Int, sortBy: String, sortOrder: String): List<DriverResponse>

    fun save(request: DriverRequest): DriverResponse

    fun softDelete(id: Long)

    fun update(request: DriverRequest): DriverResponse
}