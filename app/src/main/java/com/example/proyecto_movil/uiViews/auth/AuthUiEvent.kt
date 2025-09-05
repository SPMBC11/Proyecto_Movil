package com.example.proyecto_movil.uiViews.auth

sealed class AuthUiEvent {
    data class OnEmailChange(val value: String) : AuthUiEvent()
    data class OnPasswordChange(val value: String) : AuthUiEvent()
    data object OnLoginClick : AuthUiEvent()
    data object OnRegisterClick : AuthUiEvent()
    data object OnLogoutClick : AuthUiEvent()
    data object OnErrorConsumed : AuthUiEvent()
}