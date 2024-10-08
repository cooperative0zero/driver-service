package com.modsen.software.driver.client

import com.modsen.software.driver.configuration.FeignConfig
import com.modsen.software.driver.dto.PassengerResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "passenger-service", configuration = [FeignConfig::class])
interface PassengerServiceClient {
    @GetMapping(value = ["/api/v1/passengers/{id}"])
    fun getById(@PathVariable("id") id: Long?): PassengerResponse
}