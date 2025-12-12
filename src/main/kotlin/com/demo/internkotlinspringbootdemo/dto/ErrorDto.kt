package com.demo.internkotlinspringbootdemo.dto

data class ErrorDto(
    val code: String,
    val message: String,
    val status: Int
)