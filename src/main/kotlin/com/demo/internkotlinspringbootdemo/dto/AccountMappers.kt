package com.demo.internkotlinspringbootdemo.dto

import com.demo.internkotlinspringbootdemo.entity.Account

fun Account.toDto(): AccountDto {
    return AccountDto(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        gender = this.gender,
        phoneNumber = this.phoneNumber,
        email = this.email,
        userName = this.userName
    )
}

fun AccountDto.toEntity(placeholderPassword: String): Account {
    return Account(
        id = this.id, // DTO อาจมี ID อยู่แล้วสำหรับการอัปเดต
        firstName = this.firstName,
        lastName = this.lastName,
        gender = this.gender,
        phoneNumber = this.phoneNumber,
        email = this.email,
        userName = this.userName,
        password = placeholderPassword // ต้องใส่รหัสผ่านตามที่ Entity กำหนด
    )
}