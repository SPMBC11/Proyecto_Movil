package com.example.proyecto_movil

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Proyecto_movilTheme() {
                CritiChordApp()
            }
        }
    }
}


@Composable
fun CritiChordTopBarPreview() {
    Proyecto_movilTheme {
        CritiChordTopBar()
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES // oscuro
)
@Composable
fun CritiChordTopBarDarkPreview() {
    Proyecto_movilTheme {
        CritiChordTopBar()
    }
}
