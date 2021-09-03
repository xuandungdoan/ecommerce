package com.xuandung.ecommerce.customexception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(value = [InvalidArgs::class])
    fun handleInvalidArgument(ex: InvalidArgs): ResponseEntity<Map<String, String>> {
        val errors: MutableMap<String, String> = HashMap()
        errors["message"] = ex.message!!
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<MutableMap<String, MutableMap<String, String?>>> {
        val error: MutableMap<String, MutableMap<String, String?>> = HashMap()
        val errorDetail: MutableMap<String, String?> = HashMap()
        val errors = ex.bindingResult.fieldErrors
        for (fieldError in errors) {
            errorDetail[fieldError.field] = fieldError.defaultMessage
        }

        error["error"] = errorDetail
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}