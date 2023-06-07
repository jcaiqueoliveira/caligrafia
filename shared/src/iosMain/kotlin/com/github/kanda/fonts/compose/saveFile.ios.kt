package com.github.kanda.fonts.compose

import androidx.compose.ui.text.font.Font
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.usePinned
import okio.Okio
import okio.buffer
import okio.sink
import okio.source
import platform.Foundation.*

actual fun saveFontFile(byteArray: ByteArray, fileName: String) {
    val fileManager = FileManager.defaultManager
    val documentDirectory =
        fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask).lastObject
    val fileURL = documentDirectory.URLByAppendingPathComponent(fileName)

    byteArray.usePinned { pinned ->
        val buffer = ByteArray(pinned.get().toInt())
        pinned.addressOf(0).readBytes(buffer)

        Okio.buffer(Okio.sink(fileURL)).write(buffer)
    }
}

actual fun loadFontFile(fileName: String): ByteArray {
    val fileURL = NSFileManager.defaultManager.URLsForDirectory(
        NSDocumentDirectory,
        NSUserDomainMask
    ).lastObject
        .URLByAppendingPathComponent(fileName)
    val source = Okio.buffer(Okio.source(fileURL))
    val byteArray = source.readByteArray()
    source.close()
    return byteArray
}

actual fun getFont(): Font {
    return UIFont.systemFontOfSize(12.0)
}