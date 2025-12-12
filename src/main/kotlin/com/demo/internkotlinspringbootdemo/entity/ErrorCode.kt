package com.demo.internkotlinspringbootdemo.entity

enum class ErrorCode(
    val code: String,
    val message: String,
    val status: Int
) {
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found", 404),
    INVALID_REQUEST("INVALID_REQUEST", "Invalid request", 400),
    INTERNAL_ERROR("INTERNAL_ERROR", "Internal error", 500)
}
