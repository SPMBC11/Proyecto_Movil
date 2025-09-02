package com.example.proyecto_movil.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// === Paleta personalizada ===
private val Turquesa = Color(0xFF00C4D6) // Botones principales
private val TurquesaOscuro = Color(0xFF008B94)
private val FondoClaro = Color(0xFFFFFFFF)
private val FondoOscuro = Color(0xFF0E0E0E)

private val LightColors = lightColorScheme(
    primary            = Turquesa,
    onPrimary          = Color.White,
    primaryContainer   = Turquesa.copy(alpha = 0.15f),
    onPrimaryContainer = TurquesaOscuro,

    secondary          = Turquesa,
    onSecondary        = Color.White,
    secondaryContainer = Turquesa.copy(alpha = 0.15f),
    onSecondaryContainer = TurquesaOscuro,

    background         = FondoClaro,
    onBackground       = Color.Black,
    surface            = FondoClaro,
    onSurface          = Color.Black,

    error              = Color(0xFFB3261E),
    onError            = Color.White
)

private val DarkColors = darkColorScheme(
    primary            = Turquesa,
    onPrimary          = Color.Black,
    primaryContainer   = Turquesa.copy(alpha = 0.25f),
    onPrimaryContainer = FondoOscuro,

    secondary          = Turquesa,
    onSecondary        = Color.Black,
    secondaryContainer = Turquesa.copy(alpha = 0.25f),
    onSecondaryContainer = FondoOscuro,

    background         = FondoOscuro,
    onBackground       = Color.White,
    surface            = FondoOscuro,
    onSurface          = Color.White,

    error              = Color(0xFFCF6679),
    onError            = Color.Black
)

@Composable
fun Proyecto_movilTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
