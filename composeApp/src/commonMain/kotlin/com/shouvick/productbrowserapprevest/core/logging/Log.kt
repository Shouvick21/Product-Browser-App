package com.shouvick.productbrowserapprevest.core.logging

expect object Log {
    fun e(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    )

    fun d(
        tag: String,
        message: String,
    )

    fun i(
        tag: String,
        message: String,
    )
}
