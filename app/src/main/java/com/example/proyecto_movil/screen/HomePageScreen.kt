package com.example.proyecto_movil.screen

import androidx.compose.runtime.Composable

@Composable
fun HomePageScreen(
    onBack: () -> Unit = {},
    onLogin: (email: String, password: String, remember: Boolean) -> Unit = { _, _, _ -> },
    onForgotPassword: () -> Unit = {},
    onRegister: () -> Unit = {},
    onGoogle: () -> Unit = {},
    onFacebook: () -> Unit = {},
    onApple: () -> Unit = {},