package com.modsen.software.driver.exception

import com.fasterxml.jackson.annotation.JsonView

open class BaseCustomException(
    @field:JsonView(value = [BaseCustomException::class])
    var statusCode: Int,

    @field:JsonView(value = [BaseCustomException::class])
    var customMessage: String,
): RuntimeException()