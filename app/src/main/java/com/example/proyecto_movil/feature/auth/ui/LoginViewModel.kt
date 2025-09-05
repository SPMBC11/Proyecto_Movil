package com.example.proyecto_movil.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null
)

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    fun login(email: String, password: String, remember: Boolean) {
        if (email.isBlank() || password.isBlank()) {
            _state.update { it.copy(errorMessage = "Email y contraseña son obligatorios") }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            val result = authRepository.login(email, password)
            _state.update {
                result.fold(
                    onSuccess = { it.copy(isLoading = false, isLoggedIn = true) },
                    onFailure = { err -> it.copy(isLoading = false, errorMessage = err.message ?: "Error al iniciar sesión") }
                )
            }
        }
    }

    fun clearError() { _state.update { it.copy(errorMessage = null) } }
}

