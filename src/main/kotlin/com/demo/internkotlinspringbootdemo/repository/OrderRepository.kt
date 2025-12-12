
package com.demo.internkotlinspringbootdemo.repository

import com.demo.internkotlinspringbootdemo.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository : JpaRepository<Order, UUID> {
    fun findAllByUserId(userId: UUID): List<Order>
}