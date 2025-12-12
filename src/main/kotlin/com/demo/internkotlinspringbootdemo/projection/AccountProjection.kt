package com.demo.internkotlinspringbootdemo.projection

import java.util.UUID

interface AccountProjection {
    fun getId(): UUID?
    fun getFirstName(): String
    fun getLastName(): String
    // Method ที่ใช้คำนวณ Full Name
    fun getFullName(): String = "${getFirstName()} ${getLastName()}"
}