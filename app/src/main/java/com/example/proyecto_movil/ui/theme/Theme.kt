package com.example.proyecto_movil.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ===== Light from Material Theme Builder (values/colors.xml) =====
private val LightColors = lightColorScheme(
    primary            = Color(0xFF006876),
    onPrimary          = Color(0xFFFFFFFF),
    primaryContainer   = Color(0xFFA2EFFF),
    onPrimaryContainer = Color(0xFF004E5A),

    secondary          = Color(0xFF006874),
    onSecondary        = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF9EEFFD),
    onSecondaryContainer = Color(0xFF004F58),

    tertiary           = Color(0xFF545D7E),
    onTertiary         = Color(0xFFFFFFFF),
    tertiaryContainer  = Color(0xFFDBE1FF),
    onTertiaryContainer = Color(0xFF3C4665),

    error              = Color(0xFF904A43),
    onError            = Color(0xFFFFFFFF),
    errorContainer     = Color(0xFFFFDAD6),
    onErrorContainer   = Color(0xFF3B0907),

    background         = Color(0xFFFBFCFD),
    onBackground       = Color(0xFF191C1D),
    surface            = Color(0xFFFBFCFD),
    onSurface          = Color(0xFF191C1D),

    surfaceVariant     = Color(0xFFDBE4E7),
    onSurfaceVariant   = Color(0xFF40484A),
    outline            = Color(0xFF71787A),
    outlineVariant     = Color(0xFFBFC8CA),

    inverseOnSurface   = Color(0xFFEFF1F2),
    inverseSurface     = Color(0xFF2E3132),
    inversePrimary     = Color(0xFF83D2E3),

    scrim              = Color(0xFF000000),
    // surfaceTint omitted → por defecto usa primary
)

// ===== Dark from Material Theme Builder (values-night/colors.xml) =====
private val DarkColors = darkColorScheme(
    primary            = Color(0xFF83D2E3),
    onPrimary          = Color(0xFF00363E),
    primaryContainer   = Color(0xFF004E5A),
    onPrimaryContainer = Color(0xFFA2EFFF),

    secondary          = Color(0xFF82D3E0),
    onSecondary        = Color(0xFF00363D),
    secondaryContainer = Color(0xFF004F58),
    onSecondaryContainer = Color(0xFF9EEFFD),

    tertiary           = Color(0xFFBCC5EB),
    onTertiary         = Color(0xFF262F4D),
    tertiaryContainer  = Color(0xFF3C4665),
    onTertiaryContainer = Color(0xFFDBE1FF),

    error              = Color(0xFFFFB4AB),
    onError            = Color(0xFF561E19),
    errorContainer     = Color(0xFF73332D),
    onErrorContainer   = Color(0xFFFFDAD6),

    background         = Color(0xFF0E1416),
    onBackground       = Color(0xFFDEE3E5),
    surface            = Color(0xFF0E1416),
    onSurface          = Color(0xFFDEE3E5),

    surfaceVariant     = Color(0xFF3F484A),
    onSurfaceVariant   = Color(0xFFBFC8CA),
    outline            = Color(0xFF899295),
    outlineVariant     = Color(0xFF3F484A),

    inverseOnSurface   = Color(0xFF2B3133),
    inverseSurface     = Color(0xFFDEE3E5),
    inversePrimary     = Color(0xFF006876),

    scrim              = Color(0xFF000000),
)

@Composable
fun Proyecto_movilTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography  = Typography,   // deja tu Type.kt
        content     = content
    )
}
