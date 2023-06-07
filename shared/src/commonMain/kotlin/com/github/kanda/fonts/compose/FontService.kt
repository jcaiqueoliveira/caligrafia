package com.github.kanda.fonts.compose

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.set
import kotlinx.serialization.Serializable

internal class FontService(private val client: HttpClient = getKtorClient()) {

    suspend fun listAllFonts(key: String): List<WebFont> {
        val response = client.get("webfonts?key=$key").body<WebFontRaw>()
        return response.items
    }

    suspend fun download(fileUrl: String, fontName: String, type: String) {
        val body = client.get {
            url { set(fileUrl) }
        }.body<ByteArray>()
        saveFontFile(byteArray = body, "currentfont.ttf")
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