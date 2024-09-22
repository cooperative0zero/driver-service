package com.modsen.software.driver.repository
import com.modsen.software.driver.entity.Driver
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DriverRepository : JpaRepository<Driver, Long> {

    @Modifying
    @Query("UPDATE Driver d SET d.isDeleted = true WHERE d.id = :id")
    fun softDelete(@Param("id") id: Long): Int
}