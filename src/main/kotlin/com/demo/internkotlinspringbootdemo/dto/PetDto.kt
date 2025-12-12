package com.demo.internkotlinspringbootdemo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.UUID

data class PetDto(
    val id : UUID? = null,
    val ownerID : UUID? = null,

    @field:NotBlank(message = "Name must not be blank")
    val name : String,

    @field:NotBlank(message = "Gender must not be blank")
    val gender : String,

    @field:NotNull(message = "Gender must not be null or empty")
    val type : String
)