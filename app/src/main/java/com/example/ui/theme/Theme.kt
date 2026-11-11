package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GoldPrimary,
    onPrimary = CosmicNavyDark,
    primaryContainer = GoldDark,
    onPrimaryContainer = GoldLight,
    secondary = CelestialCyan,
    onSecondary = CosmicNavyDark,
    background = CosmicNavyDark,
    onBackground = StarlightWhite,
    surface = CosmicNavyCard,
    onSurface = StarlightWhite,
    surfaceVariant = CosmicNavyCardElevated,
    onSurfaceVariant = TextMuted,
    outline = CosmicNavyBorder
)

private val LightColorScheme = lightColorScheme(
    primary = GoldDark,
    onPrimary = Color.White,
    primaryContainer = GoldLight,
    onPrimaryContainer = CosmicNavyDark,
    secondary = CelestialCyan,
    onSecondary = CosmicNavyDark,
    background = SurfaceLight,
    onBackground = TextDark,
    surface = SurfaceCardLight,
    onSurface = TextDark,
    surfaceVariant = Color(0xFFECEFF8),
    onSurfaceVariant = TextSecondaryLight,
    outline = Color(0xFFD0D6E6)
)

@Composable
fun LawStudyGuideTheme(
    darkTheme: Boolean = true, // Default to stunning immersive dark theme
    dynamicColor: Boolean = false, // Keep tailored brand palette by default
    content: @Composable () -> Unit,
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
