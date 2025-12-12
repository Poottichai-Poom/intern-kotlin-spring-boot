package com.demo.internkotlinspringbootdemo.dto

import com.demo.internkotlinspringbootdemo.constants.GenderConstants
import jakarta.validation.constraints.*
import java.util.UUID

data class AccountDto(

    val id: UUID? = null,

    @field:NotBlank(message = "firstName must not be blank")
    val firstName: String,

    @field:NotBlank(message = "lastName must not be blank")
    val lastName: String,

    @field:NotNull(message = "gender is required")
    val gender: GenderConstants,

    @field:Pattern(
        regexp = "^[0-9]10}$",
        message = "phoneNumber must be 9–10 digits"
    )
    val phoneNumber: String? = null,

    @field:Email(message = "email format is invalid")
    val email: String? = null,

    @field:NotBlank(message = "birthDate must not be blank")
    val userName: String? = null
)
