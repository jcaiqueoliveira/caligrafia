package com.github.kanda.fonts.compose

import platform.Foundation.NSHomeDirectory

actual class Caligrafia {

    companion object {
        suspend fun init(key: String, vararg fonts: String) {
            CaligrafiaInternals.setDir(NSHomeDirectory())
            CaligrafiaInternals.init(key, *fonts)
        }
    }
}