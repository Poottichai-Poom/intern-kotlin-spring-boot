package com.demo.internkotlinspringbootdemo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.UUID

data class PetDto(
    val id: UUID,
    val ownerId: UUID,

    @field:NotBlank(message = "name must not be blank")
    val name: String,

    @field:NotNull(message = "gender is required")
    val gender: String,

    @field:NotNull(message = "type must not be blank")
    val type: String,
)