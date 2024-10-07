package com.modsen.software.driver.configuration

import feign.Retryer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class FeignConfig {
    @Bean
    fun retryer(): Retryer {
        return Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 5)
    }
}