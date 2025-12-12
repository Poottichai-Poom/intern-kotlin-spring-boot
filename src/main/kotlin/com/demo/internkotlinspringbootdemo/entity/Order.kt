package com.demo.internkotlinspringbootdemo.entity

import com.demo.internkotlinspringbootdemo.constants.OrderStatus
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @Column(name = "id")
    val id: UUID? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "price", nullable = false)
    val price: BigDecimal,

    @Column(name = "address", nullable = false)
    val address: String,

    @Column(name = "order_date", nullable = false)
    val orderDate: LocalDateTime,

    @Column(name = "order_status", nullable = false)
    @Enumerated(STRING)
    val orderStatus: OrderStatus,

    // Foreign Key ไปยัง Account
    @Column(name = "user_id", nullable = false)
    val userId: UUID
)