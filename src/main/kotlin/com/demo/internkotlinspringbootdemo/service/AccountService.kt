package com.demo.internkotlinspringbootdemo.service

import com.demo.internkotlinspringbootdemo.dto.AccountDto
import com.demo.internkotlinspringbootdemo.entity.Account
import com.demo.internkotlinspringbootdemo.projection.AccountProjection // Import ที่สร้างขึ้น
import com.demo.internkotlinspringbootdemo.repository.AccountRepository
import com.demo.internkotlinspringbootdemo.dto.toDto
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccountService(private val accountRepository: AccountRepository) {

    companion object {
        private const val ACCOUNT_CACHE = "accounts"
    }
    // --- Caching Methods ---

    // 1. ใช้ Cache สำหรับ findById (ค้นหา Account Entity)
    @Cacheable(value = [ACCOUNT_CACHE], key = "#id")
    fun getAccountEntityById(id: UUID): Account? {
        println("Fetching Account Entity from Database for ID: $id") // Logging เพื่อดูว่ามีการเรียก DB จริงหรือไม่
        return accountRepository.findByIdOrNull(id)
    }

    // 2. Projection method: ไม่ใช้ Cache โดยตรง แต่ใช้ Entity ที่อาจ Cache อยู่แล้ว
    fun getAccountProjectionById(id: UUID): AccountProjection? {
        return accountRepository.findAccountProjectionById(id)
    }

    // 3. สร้าง/อัพเดท (CachePut)
    @CachePut(value = [ACCOUNT_CACHE], key = "#result.id")
    fun updateAccount(id: UUID, accountDto: AccountDto): AccountDto {
        val existingAccount = accountRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Account not found with id $id")

        val updatedEntity = existingAccount.copy(
            firstName = accountDto.firstName,
            lastName = accountDto.lastName,
            gender = accountDto.gender,
            phoneNumber = accountDto.phoneNumber,
            email = accountDto.email,
            userName = accountDto.userName
        )
        val savedAccount = accountRepository.save(updatedEntity)
        return savedAccount.toDto()
    }

    // 4. ลบ (CacheEvict)
    @CacheEvict(value = [ACCOUNT_CACHE], key = "#id")
    fun deleteAccount(id: UUID) {
        accountRepository.deleteById(id)
    }
}