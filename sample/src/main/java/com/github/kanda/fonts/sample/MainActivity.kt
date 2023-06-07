package com.github.kanda.fonts.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.github.kanda.fonts.compose.Caligrafia
import com.github.kanda.fonts.compose.getFont
import com.github.kanda.fonts.sample.ui.theme.FontsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var showWithCustomFont by remember { mutableStateOf(false) }

            val scope = rememberCoroutineScope()
            LaunchedEffect(key1 = Unit) {
                scope.launch {
                    Caligrafia
                        .getInstance(context)
                        .init(BuildConfig.WEB_KEY_TOKEN)
                }
            }
            FontsTheme {
                // A surface container using the 'background' color from the theme
                LaunchedEffect(key1 = Unit) {
                    delay(3_000)
                    showWithCustomFont = true
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(text = "Dale")
                    AnimatedVisibility(showWithCustomFont) {
                        val fontFamily = FontFamily(getFont())
                        Text(text = "Dale", fontFamily = fontFamily)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FontsTheme {
        Greeting("Android")
    }
}