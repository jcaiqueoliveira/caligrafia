package com.github.kanda.fonts.compose

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.extensions.storeOf
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.set
import kotlinx.serialization.Serializable

internal class FontService(private val client: HttpClient = getKtorClient()) {

    private val store: KStore<ByteArray> = storeOf(filePath = "my-awesome-font.ttf", 0)

    suspend fun listAllFonts(key: String): List<WebFont> {
        val response = client.get("webfonts?key=$key").body<WebFontRaw>()
        return response.items
    }

    suspend fun download(fileUrl: String, fontName: String, type: String) {
        val body = client.get {
            url { set(fileUrl) }
        }.body<ByteArray>()

        store.set(body)
    }
}

@Serializable
internal data class WebFont(
    val family: String,
    val variants: List<String>,
    val subsets: List<String>,
    val version: String,
    val lastModified: String,
    val files: Map<String, String>,
    val category: String,
    val kind: String,
    val menu: String
)

@Serializable
internal data class WebFontRaw(
    val items: List<WebFont>
)