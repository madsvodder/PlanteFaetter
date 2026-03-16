package com.example.planteapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform