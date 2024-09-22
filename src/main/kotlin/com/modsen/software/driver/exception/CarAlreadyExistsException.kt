package com.modsen.software.driver.exception

import org.springframework.http.HttpStatus

class CarAlreadyExistsException(message: String): BaseCustomException(HttpStatus.BAD_REQUEST.value(), message)