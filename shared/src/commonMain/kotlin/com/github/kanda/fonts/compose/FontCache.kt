package com.github.kanda.fonts.compose

import kotlinx.serialization.Serializable

@Serializable
internal data class FontCache(
    val font: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as FontCache

        if (!font.contentEquals(other.font)) return false

        return true
    }

    override fun hashCode(): Int {
        return font.contentHashCode()
    }
}
