package com.github.kanda.fonts.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import okio.buffer
import okio.sink
import java.io.File

actual fun saveFontFile(byteArray: ByteArray, fileName: String) {
    val file = File(CaligrafiaInternals.directory, fileName)

    file.deleteOnExit()
    file.sink().buffer().use { sink ->
        sink.write(byteArray)
    }
}

@Composable
actual fun getFont(): Font {
    val context = LocalContext.current
    val file = File(context.filesDir, "currentfont.ttf")
    return Font(file)
}