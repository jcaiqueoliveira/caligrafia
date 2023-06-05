package com.github.kanda.fonts.compose

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun getKtorEngine(): HttpClientEngine = HttpClient(OkHttp).engine