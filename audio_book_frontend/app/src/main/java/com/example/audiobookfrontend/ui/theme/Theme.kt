package com.example.audiobookfrontend.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Accent,
    background = Background,
    surface = SurfaceLight,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onTertiary = OnAccent
)

/**
 * PUBLIC_INTERFACE
 * Top-level theme composable for the AudioBook app using Material 3.
 * Applies custom color scheme, typography, and shapes.
 */
@Composable
fun AudioBookTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(),
        shapes = Shapes,
        content = content
    )
}
