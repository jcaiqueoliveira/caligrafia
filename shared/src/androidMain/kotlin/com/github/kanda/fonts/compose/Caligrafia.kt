package com.github.kanda.fonts.compose

import android.content.Context

actual class Caligrafia private constructor() {

    companion object {
        fun getInstance(context: Context): Caligrafia {
            CaligrafiaInternals.setDir(context.filesDir.path)
            return Caligrafia()
        }
    }

    suspend fun init(key: String, vararg fonts: String) {
        CaligrafiaInternals.init(key, *fonts)
    }
}