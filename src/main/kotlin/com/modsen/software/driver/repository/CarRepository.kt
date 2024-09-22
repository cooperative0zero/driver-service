package com.modsen.software.driver.repository

import com.modsen.software.driver.entity.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long> {

    @Modifying
    @Query("UPDATE Car c SET c.isDeleted = true WHERE c.id = :id")
    fun softDelete(@Param("id") id: Long): Int
}