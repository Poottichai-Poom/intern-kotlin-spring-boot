package com.demo.internkotlinspringbootdemo.projection

import org.springframework.beans.factory.annotation.Value
import java.util.UUID

interface AccountProjection {
    fun getId(): UUID?
    fun getFirstName(): String
    fun getLastName(): String

    @Value("#{target.firstName + ' ' + target.lastName}")
    fun getFullName(): String
}
