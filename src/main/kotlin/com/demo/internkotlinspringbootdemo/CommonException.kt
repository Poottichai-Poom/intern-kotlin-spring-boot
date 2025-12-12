package com.demo.internkotlinspringbootdemo

import com.demo.internkotlinspringbootdemo.entity.ErrorCode

class CommonException(
    val errorCode: ErrorCode,
    message: String? = null
) : RuntimeException(message ?: errorCode.message)
