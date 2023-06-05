package com.github.kanda.fonts.compose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform