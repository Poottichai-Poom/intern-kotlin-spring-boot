package com.demo.internkotlinspringbootdemo.controller

import com.demo.internkotlinspringbootdemo.dto.AccountDto
import com.demo.internkotlinspringbootdemo.projection.AccountProjection
import com.demo.internkotlinspringbootdemo.service.AccountService
import com.demo.internkotlinspringbootdemo.service.toDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/accounts")
class AccountController(private val accountService: AccountService) {

    @PostMapping
    fun createAccount(@Valid @RequestBody accountDto: AccountDto): ResponseEntity<AccountDto> {
        return ResponseEntity(accountDto.copy(id = UUID.randomUUID()), HttpStatus.CREATED)
    }

    @GetMapping("/{id}/entity")
    fun getAccountEntity(@PathVariable id: UUID): ResponseEntity<AccountDto> {
        // ใช้ getAccountEntityById ซึ่งมีการใช้ @Cacheable
        val accountEntity = accountService.getAccountEntityById(id)

        return accountEntity?.let {
            // ต้องมีการแปลง Entity เป็น DTO ด้วย toDto() ก่อนส่งกลับ
            // เนื่องจากเราไม่ได้รวม Extension function toDto ในไฟล์นี้
            // เราจะสมมติว่ามีการแปลงและส่งกลับเป็น DTO ที่มีข้อมูลครบถ้วน

            // ⚠️ Warning: toDto() must be accessible here!
            val accountDto = it.toDto()
            ResponseEntity.ok(accountDto)
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/{id}/projection")
    fun getAccountProjection(@PathVariable id: UUID): ResponseEntity<AccountProjection> {
        // ใช้ findAccountProjectionById ที่ใช้ Projection Interface
        val projection = accountService.getAccountProjectionById(id)

        return projection?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PutMapping("/{id}")
    fun updateAccount(@PathVariable id: UUID, @Valid @RequestBody accountDto: AccountDto): ResponseEntity<AccountDto> {
        return try {
            // ใช้ updateAccount ซึ่งมีการใช้ @CachePut
            val updatedAccount = accountService.updateAccount(id, accountDto)
            ResponseEntity.ok(updatedAccount)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteAccount(@PathVariable id: UUID): ResponseEntity<Void> {
        accountService.deleteAccount(id) // ใช้ deleteAccount ซึ่งมีการใช้ @CacheEvict
        return ResponseEntity.noContent().build()
    }
}