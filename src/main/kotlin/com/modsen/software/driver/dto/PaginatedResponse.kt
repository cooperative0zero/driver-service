package com.modsen.software.driver.dto

data class PaginatedResponse<T> (
    val items: List<T>,
    val page: Int,
    val size: Int,
    val total: Int
)