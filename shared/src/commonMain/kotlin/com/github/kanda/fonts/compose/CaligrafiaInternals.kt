package com.github.kanda.fonts.compose

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
internal object CaligrafiaInternals {

    @PublishedApi
    internal var directory: String = ""
    suspend fun init(
        key: String,
        vararg fonts: String
    ) {
        val service = FontService()
        val result = service.listAllFonts(key)
        val font = "work sans"
        val fontFamily = result.first { it.family.equals(font, ignoreCase = true) }
        println(fontFamily)
        val fileToDownload = fontFamily.files["600"]!!
        println(fileToDownload)
        service.download(fileToDownload, fontFamily.family, "600")
    }

    fun setDir(dir: String) {
        directory = dir
    }
}