package com.demo.internkotlinspringbootdemo

import com.demo.internkotlinspringbootdemo.constants.ErrorCode

class CommonException(
    val errorCode: ErrorCode,
    message: String? = null
) : RuntimeException(message ?: errorCode.message)
