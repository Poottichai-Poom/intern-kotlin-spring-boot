package com.demo.internkotlinspringbootdemo.controller

import com.demo.internkotlinspringbootdemo.CommonException
import com.demo.internkotlinspringbootdemo.dto.ErrorDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CommonException::class)
    fun handleCommonException(ex: CommonException): ResponseEntity<ErrorDto> {
        val body = ErrorDto(
            code = ex.errorCode.code,
            message = ex.message ?: "",
            status = ex.errorCode.status
        )
        return ResponseEntity.status(ex.errorCode.status).body(body)
    }
}

