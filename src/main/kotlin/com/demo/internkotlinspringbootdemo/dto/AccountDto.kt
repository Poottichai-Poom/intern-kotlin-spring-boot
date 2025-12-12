package com.demo.internkotlinspringbootdemo.dto

import com.demo.internkotlinspringbootdemo.constants.GenderConstants
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import java.util.UUID

data class AccountDto(
    val id: UUID? = null,

    @field:NotBlank(message ="firstName must not be blank")
    val firstName: String,

    @field:NotBlank(message ="lastName must not be blank")
    val lastName: String,

    @field:NotNull(message ="gender is required")
    val gender: GenderConstants,

    @field:Pattern(
        regexp = "^[0-9]{9,10}$",
        message = "phoneNumber must be 10 digits"
    )
    val phoneNumber: String? = null,

    @field:Email(message = "email must not be blank")
    val email: String?,

    @field:NotBlank(message = "Username must not be blank")
    val userName: String?
)