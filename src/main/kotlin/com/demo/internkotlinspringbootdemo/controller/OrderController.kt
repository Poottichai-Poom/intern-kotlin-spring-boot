package com.demo.internkotlinspringbootdemo.controller

import com.demo.internkotlinspringbootdemo.dto.OrderDto
import com.demo.internkotlinspringbootdemo.service.OrderService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createOrder(@Valid @RequestBody orderDto: OrderDto): ResponseEntity<OrderDto> {
        val createdOrder = orderService.createOrder(orderDto)
        return ResponseEntity(createdOrder, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: UUID): ResponseEntity<OrderDto> {
        // ใช้ getOrderById ซึ่งมีการใช้ @Cacheable
        val orderDto = orderService.getOrderById(id)

        return orderDto?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PutMapping("/{id}")
    fun updateOrder(@PathVariable id: UUID, @Valid @RequestBody orderDto: OrderDto): ResponseEntity<OrderDto> {
        return try {
            // ใช้ updateOrder ซึ่งมีการใช้ @CachePut
            val updatedOrder = orderService.updateOrder(id, orderDto)
            ResponseEntity.ok(updatedOrder)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: UUID): ResponseEntity<Void> {
        orderService.deleteOrder(id) // ใช้ deleteOrder ซึ่งมีการใช้ @CacheEvict
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/user/{userId}")
    fun getOrdersByUserId(@PathVariable userId: UUID): ResponseEntity<List<OrderDto>> {
        // ใช้ getOrdersByUserId ซึ่งมีการใช้ Repository method findAllByUserId
        val orders = orderService.getOrdersByUserId(userId)

        return if (orders.isNotEmpty()) {
            ResponseEntity.ok(orders)
        } else {
            // อาจส่งเป็น 204 No Content หรือ 200 OK พร้อมรายการว่าง ขึ้นอยู่กับข้อกำหนด
            ResponseEntity.ok(emptyList())
        }
    }
}