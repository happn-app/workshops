package com.happn.kmp101

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform