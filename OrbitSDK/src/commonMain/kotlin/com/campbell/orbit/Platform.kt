package com.campbell.orbit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform