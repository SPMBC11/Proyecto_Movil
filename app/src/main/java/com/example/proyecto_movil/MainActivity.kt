package com.example.proyecto_movil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.navigation.AppNavHost

// 👇 Ajusta este import según dónde esté AppLogo en tu proyecto
// Si lo creaste en ui/utils:
import com.example.proyecto_movil.utils.AppLogo
// Si lo dejaste en utils (sin ui):
// import com.example.proyecto_movil.utils.AppLogo

// Usa tu fondo existente
import com.example.proyecto_movil.screen.BackGroundImage

import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_movilTheme {
                // Splash breve con logo
                var showSplash by remember { mutableStateOf(true) }
                LaunchedEffect(Unit) {
                    delay(1500) // 1.5 s (ajusta si quieres)
                    showSplash = false
                }

                if (showSplash) {
                    SplashScreen()
                } else {
                    AppNavHost()
                }
            }
        }
    }
}

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        BackGroundImage()
        AppLogo(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

// (Opcional) Mantener Greeting:
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier.padding(16.dp))
}
