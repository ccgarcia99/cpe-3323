package com.example.decierdo_pe322.ui.theme

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

// Custom Winter Blue Theme Colors
private val WinterBlueDarkColorScheme = darkColorScheme(
    primary = WinterBlueDarkPrimary,
    onPrimary = WinterBlueDarkOnPrimary,
    primaryContainer = WinterBlueDarkPrimaryContainer,
    secondary = WinterBlueDarkSecondary,
    onSecondary = WinterBlueDarkOnSecondary,
    background = WinterBlueDarkBackground,
    onBackground = WinterBlueDarkOnBackground,
    surface = WinterBlueDarkSurface,
    onSurface = WinterBlueDarkOnSurface
)

private val WinterBlueLightColorScheme = lightColorScheme(
    primary = WinterBlueLightPrimary,
    onPrimary = WinterBlueLightOnPrimary,
    primaryContainer = WinterBlueLightPrimaryContainer,
    secondary = WinterBlueLightSecondary,
    onSecondary = WinterBlueLightOnSecondary,
    background = WinterBlueLightBackground,
    onBackground = WinterBlueLightOnBackground,
    surface = WinterBlueLightSurface,
    onSurface = WinterBlueLightOnSurface
)

@Composable
fun Decierdo_PE322Theme(
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
        darkTheme -> WinterBlueDarkColorScheme
        else -> WinterBlueLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
