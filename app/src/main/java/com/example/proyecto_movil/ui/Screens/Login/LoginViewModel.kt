package com.example.proyecto_movil.ui.Screens.Login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState

    fun updateEmail(v: String) = _uiState.update { s -> s.copy(email = v) }
    fun updatePassword(v: String) = _uiState.update { s -> s.copy(password = v) }
    fun toggleShowPassword() = _uiState.update { s -> s.copy(showPassword = !s.showPassword) }
    fun toggleRemember() = _uiState.update { s -> s.copy(remember = !s.remember) }

    fun onBackClicked() = _uiState.update { s -> s.copy(navigateBack = true) }
    fun onForgotClicked() = _uiState.update { s -> s.copy(navigateToForgot = true) }
    fun onRegisterClicked() = _uiState.update { s -> s.copy(navigateToRegister = true) }


    //comentario
    fun onLoginClicked() {
        val s = _uiState.value
        when {
            s.email.isBlank() || s.password.isBlank() ->
                showError("Completa email y contraseña")
            s.password.length < 6 ->
                showError("La contraseña debe tener al menos 6 caracteres")
            else ->
                _uiState.update { it.copy(navigateAfterLogin = true, showMessage = false, errorMessage = "") }
        }
    }

    fun consumeBack() = _uiState.update { s -> s.copy(navigateBack = false) }
    fun consumeAfterLogin() = _uiState.update { s -> s.copy(navigateAfterLogin = false) }
    fun consumeForgot() = _uiState.update { s -> s.copy(navigateToForgot = false) }
    fun consumeRegister() = _uiState.update { s -> s.copy(navigateToRegister = false) }
    fun consumeMessage() = _uiState.update { s -> s.copy(showMessage = false, errorMessage = "") }

    private fun showError(msg: String) {
        _uiState.update { s -> s.copy(errorMessage = msg, showMessage = true) }
    }
}
