package com.example.proyecto_movil.uiViews.auth

import com.example.proyecto_movil.uiViews.common.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.delay

class AuthViewModel : BaseViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _oneShot = MutableSharedFlow<String>()
    val oneShot: SharedFlow<String> = _oneShot.asSharedFlow()

    // TODO: conecta con tu UserRepository cuando decidas DI o factoría
    // private val userRepo = UserRepository()

    fun onEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.OnEmailChange -> _uiState.update { it.copy(email = event.value, error = null) }
            is AuthUiEvent.OnPasswordChange -> _uiState.update { it.copy(password = event.value, error = null) }
            AuthUiEvent.OnLoginClick -> doLogin()
            AuthUiEvent.OnRegisterClick -> doRegister()
            AuthUiEvent.OnLogoutClick -> doLogout()
            AuthUiEvent.OnErrorConsumed -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun doLogin() = launchSafely {
        _uiState.update { it.copy(isLoading = true, error = null) }
        try {
            delay(500) // simula red
            val email = uiState.value.email
            val pass = uiState.value.password
            if (email.isBlank() || pass.isBlank()) error("Completa email y contraseña")
            // val ok = userRepo.login(email, pass)
            _uiState.update { it.copy(isLoading = false, isAuthenticated = true) }
            _oneShot.emit("Inicio de sesión exitoso")
        } catch (t: Throwable) {
            _uiState.update { it.copy(isLoading = false, error = t.message ?: "Error de login") }
        }
    }

    private fun doRegister() = launchSafely {
        _uiState.update { it.copy(isLoading = true, error = null) }
        try {
            delay(600)
            // userRepo.register(uiState.value.email, uiState.value.password)
            _uiState.update { it.copy(isLoading = false, isAuthenticated = true) }
            _oneShot.emit("Registro exitoso")
        } catch (t: Throwable) {
            _uiState.update { it.copy(isLoading = false, error = t.message ?: "Error de registro") }
        }
    }

    private fun doLogout() = launchSafely {
        // userRepo.logout()
        _uiState.update { AuthUiState() }
        _oneShot.emit("Sesión cerrada")
    }
}