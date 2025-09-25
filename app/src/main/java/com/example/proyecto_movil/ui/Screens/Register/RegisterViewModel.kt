package com.example.proyecto_movil.ui.Screens.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.data.repository.AuthRepository
// import com.example.proyecto_movil.data.repository.UserRepository // <- opcional si guardas perfil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    // private val userRepository: UserRepository // <- descomenta si lo usas
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState

    fun updateNombrePersona(input: String) = _uiState.update { it.copy(nombrePersona = input) }
    fun updateNombreUsuario(input: String) = _uiState.update { it.copy(nombreUsuario = input) }
    fun updateEmail(input: String)        = _uiState.update { it.copy(email = input) }
    fun updatePassword(input: String)     = _uiState.update { it.copy(password = input) }
    fun toggleMostrarPassword()           = _uiState.update { it.copy(mostrarPassword = !it.mostrarPassword) }
    fun toggleAcceptedTerms()             = _uiState.update { it.copy(acceptedTerms = !it.acceptedTerms) }

    fun onBackClicked()  = _uiState.update { it.copy(navigateBack = true) }
    fun onLoginClicked() = _uiState.update { it.copy(navigateToLogin = true) }

    fun onRegisterClicked() {
        val s = _uiState.value
        when {
            s.nombrePersona.isBlank() || s.nombreUsuario.isBlank() || s.email.isBlank() || s.password.isBlank() ->
                showError("Completa todos los campos")
            !s.acceptedTerms ->
                showError("Debes aceptar los términos y condiciones")
            s.password.length < 6 ->
                showError("La contraseña debe tener al menos 6 caracteres")
            else -> {
                viewModelScope.launch {


                        val result = authRepository.signUp(email = s.email, password = s.password)

                        if (result.isSuccess) {
                            // 2) (Opcional) Guardar info adicional del usuario
                            val uid = authRepository.currentUser?.uid
                                ?: throw Exception("No se pudo obtener el usuario actual")


                            _uiState.update {
                                it.copy(
                                    navigateAfterRegister = true,
                                    showMessage = false,
                                    errorMessage = ""
                                )
                            }
                        } else {

                            _uiState.update { it.copy(showMessage = true, errorMessage = result.exceptionOrNull()?.message ?: "Error al registrar") }
                        }

                }
            }
        }
    }

    fun consumeNavigation() = _uiState.update {
        it.copy(navigateBack = false, navigateToLogin = false, navigateAfterRegister = false)
    }

    fun consumeMessage() = _uiState.update { it.copy(showMessage = false, errorMessage = "") }

    private fun showError(message: String) {
        _uiState.update { it.copy(errorMessage = message, showMessage = true) }
    }
}
