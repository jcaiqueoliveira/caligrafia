package com.github.kanda.fonts.compose

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.http.set
import io.ktor.http.takeFrom
import kotlinx.serialization.Serializable

internal class FontService(private val client: HttpClient = getKtorClient()) {

    suspend fun listAllFonts(key: String): List<WebFont> {
        val response = client.get("webfonts?key=$key").body<WebFontRaw>()
        return response.items
    }

    suspend fun download(fileUrl: String, fontName: String, type: String) {
        val client1 = HttpClient {
            defaultRequest {
                url(fileUrl)
            }
            HttpResponseValidator {
                validateResponse { response: HttpResponse ->
                    require(response.status.isSuccess()) {
                        "HTTP request failed with status code ${response.status.value}"
                    }
                }
            }
        }
        val response = client1.get(fileUrl).body<ByteArray>()

        saveFontFile(byteArray = response, "currentfont.ttf")
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