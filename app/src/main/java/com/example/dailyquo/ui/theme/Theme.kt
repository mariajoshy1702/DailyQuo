package com.example.dailyquo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColors = lightColorScheme(
    primary = Orange900,
    onPrimary = Color.White,
    secondary = Orange500,
    onSecondary = Color.White,
    tertiary = Orange100,
    onTertiary = Orange900,
    background = BackgroundColor,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
)

private val darkColors = darkColorScheme(
    primary = Orange900,
    onPrimary = Color.White,
    secondary = Orange500,
    onSecondary = Color.Black,
    tertiary = Orange100,
    onTertiary = Orange900,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
)

@Composable
fun DAILYQUOTheme(
    darkTheme: Boolean = androidx.compose.foundation.isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColors else lightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

