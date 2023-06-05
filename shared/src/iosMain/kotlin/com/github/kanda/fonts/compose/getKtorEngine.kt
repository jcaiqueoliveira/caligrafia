package com.github.kanda.fonts.compose

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

internal actual fun getKtorEngine(): HttpClientEngine = Darwin.create()