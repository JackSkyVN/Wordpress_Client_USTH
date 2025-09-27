package com.example.wordpressclient.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Orange40,
    secondary = Orange20,
    tertiary = Orange80
)

private val LightColorScheme = lightColorScheme(
    primary = Orange40,
    secondary = Orange20,
    tertiary = Orange80
)

@Composable
fun WordpressClientTheme(
    darkTheme: Boolean = false, // bạn có thể thay bằng isSystemInDarkTheme() nếu muốn tự động
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
