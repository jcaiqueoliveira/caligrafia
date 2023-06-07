package com.github.kanda.fonts.compose

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun createJson(): Json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    prettyPrint = true
}

private const val baseUrl = "https://www.googleapis.com/webfonts/v1/"

internal fun getKtorClient(
    base: String = baseUrl
) = HttpClient(getKtorEngine()) {
    install(ContentNegotiation) {
        json(createJson())
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    defaultRequest {
        url(base)
    }
}