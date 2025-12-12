package com.demo.internkotlinspringbootdemo.dto

import com.demo.internkotlinspringbootdemo.constants.OrderStatus
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OrderDto(
    val id: UUID? = null,

    @field:NotBlank(message = "Order name must not be blank")
    val name: String,

    @field:NotNull(message = "Price is required")
    @field:DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    val price: BigDecimal,

    @field:NotBlank(message = "Address must not be blank")
    val address: String,

    val orderDate: LocalDateTime? = LocalDateTime.now(), // กำหนดค่าเริ่มต้นได้

    @field:NotNull(message = "Order status is required")
    val orderStatus: OrderStatus,

    @field:NotNull(message = "User ID is required")
    val userId: UUID
)