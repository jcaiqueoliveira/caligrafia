package com.github.kanda.fonts.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.delay

@Composable
public inline fun rememberFont(): FontFamily = remember {
    FontFamily()
}

expect fun getFont(): Font