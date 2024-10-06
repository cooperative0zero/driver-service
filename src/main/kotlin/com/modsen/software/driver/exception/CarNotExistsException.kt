package com.modsen.software.driver.exception

import org.springframework.http.HttpStatus

class CarNotExistsException(message: String): BaseCustomException(HttpStatus.NOT_FOUND.value(), message)