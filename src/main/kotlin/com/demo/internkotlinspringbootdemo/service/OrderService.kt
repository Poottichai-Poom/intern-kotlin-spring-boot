package com.demo.internkotlinspringbootdemo.service

import com.demo.internkotlinspringbootdemo.dto.OrderDto
import com.demo.internkotlinspringbootdemo.entity.Order
import com.demo.internkotlinspringbootdemo.repository.OrderRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService(private val orderRepository: OrderRepository) {

    companion object {
        private const val ORDER_CACHE = "orders" // ชื่อ Cache สำหรับ Order
    }

    // --- Mapper (Entity <-> DTO) ---

    private fun Order.toDto() = OrderDto(id, name, price, address, orderDate, orderStatus, userId)

    private fun OrderDto.toEntity() = Order(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        price = this.price,
        address = this.address,
        orderDate = this.orderDate ?: throw IllegalArgumentException("Order date cannot be null"),
        orderStatus = this.orderStatus,
        userId = this.userId
    )

    // --- CRUD Operations ---

    fun createOrder(orderDto: OrderDto): OrderDto {
        val newOrder = orderDto.toEntity()
        val savedOrder = orderRepository.save(newOrder)
        return savedOrder.toDto()
    }

    // 1. ใช้ Cache สำหรับ findById
    @Cacheable(value = [ORDER_CACHE], key = "#id")
    fun getOrderById(id: UUID): OrderDto? {
        println("Fetching Order from Database for ID: $id")
        return orderRepository.findByIdOrNull(id)?.toDto()
    }

    // 2. อัพเดท cache หากมีการอัพเดท order
    @CachePut(value = [ORDER_CACHE], key = "#id")
    fun updateOrder(id: UUID, orderDto: OrderDto): OrderDto {
        val existingOrder = orderRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Order not found with id $id")

        val updatedEntity = existingOrder.copy(
            name = orderDto.name,
            price = orderDto.price,
            address = orderDto.address,
            orderStatus = orderDto.orderStatus,
            userId = orderDto.userId
        )
        val savedOrder = orderRepository.save(updatedEntity)
        return savedOrder.toDto()
    }

    // 3. ลบ cache หากมีการลบ order
    @CacheEvict(value = [ORDER_CACHE], key = "#id")
    fun deleteOrder(id: UUID) {
        orderRepository.deleteById(id)
    }

    // 4. Method ที่เช็คว่า user คนนี้มี order อะไรบ้าง
    fun getOrdersByUserId(userId: UUID): List<OrderDto> {
        return orderRepository.findAllByUserId(userId).map { it.toDto() }
    }
}