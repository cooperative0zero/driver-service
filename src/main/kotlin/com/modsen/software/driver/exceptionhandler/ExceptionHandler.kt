package com.modsen.software.driver.exceptionhandler

import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.modsen.software.driver.exception.BaseCustomException
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(BaseCustomException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @JsonView(BaseCustomException::class)
    fun handleCustomException(baseCustomException: BaseCustomException) = baseCustomException

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(e: ConstraintViolationException): List<String> {
        return e.constraintViolations.stream()
            .map { violation: ConstraintViolation<*> ->
                violation.propertyPath.toString() + " " + violation.message
            }
            .collect(Collectors.toList())
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentsValidationException(e: MethodArgumentNotValidException): List<String> {
        return e.bindingResult.fieldErrors.stream()
            .map { error: FieldError -> error.field + " " + error.defaultMessage }
            .collect(Collectors.toList())
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException): String {
        val errors = mutableMapOf<String, String>()

        val rootCause = ex.rootCause
        if (rootCause is JsonMappingException) {

            val path = rootCause.path.joinToString(".") { it.fieldName }
            errors[path] = "Field is required and cannot be null"
        } else {
            errors["error"] = "Malformed JSON request"
        }

        return errors.toString()
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCommonException(e: Exception) = "Exception: " + e.message
}