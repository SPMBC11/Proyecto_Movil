package com.example.proyecto_movil.ui.Screens.Login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val remember: Boolean = false,
    val showPassword: Boolean = false,
    val errorMessage: String = "",
    val showMessage: Boolean = false,
    val navigateBack: Boolean = false,
    val navigateAfterLogin: Boolean = false,
    val navigateToForgot: Boolean = false,
    val navigateToRegister: Boolean = false
)
