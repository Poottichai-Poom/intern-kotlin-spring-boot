package com.demo.internkotlinspringbootdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
@SpringBootApplication
open class InternKotlinSpringBootDemoApplication

fun main(args: Array<String>) {
    runApplication<InternKotlinSpringBootDemoApplication>(*args)
}
