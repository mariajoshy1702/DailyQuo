package com.example.dailyquo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColors = lightColorScheme(
    primary = Orange900,
    onPrimary = Color.White,
    secondary = Orange500,
    onSecondary = Color.White,
    tertiary = Orange100,
    onTertiary = Color.White,
    background = BackgroundColor,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
)

@Composable
fun DAILYQUOTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColors,
        typography = Typography,
        content = content
    )
}
/*
@Composable
fun DAILYQUOTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

 */