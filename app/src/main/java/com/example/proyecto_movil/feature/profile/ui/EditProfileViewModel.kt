package com.example.proyecto_movil.feature.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.data.local.ProfileInfo
import com.example.proyecto_movil.feature.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EditProfileUiState(
    val nombrePersona: String = "",
    val nombreUsuario: String = "",
    val email: String = "",
    val isSaving: Boolean = false,
    val isSaved: Boolean = false,
    val errorMessage: String? = null
)

class EditProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EditProfileUiState())
    val state: StateFlow<EditProfileUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            // Initialize with current profile
            profileRepository.getProfileInfo().collect { profile ->
                _state.update {
                    it.copy(
                        nombrePersona = profile.username,
                        nombreUsuario = profile.username,
                        email = "user@example.com" // Not present in mock; placeholder
                    )
                }
            }
        }
    }

    fun onNombrePersonaChange(value: String) = _state.update { it.copy(nombrePersona = value) }
    fun onNombreUsuarioChange(value: String) = _state.update { it.copy(nombreUsuario = value) }
    fun onEmailChange(value: String) = _state.update { it.copy(email = value) }

    fun save() {
        val current = _state.value
        if (current.nombrePersona.isBlank() || current.nombreUsuario.isBlank() || current.email.isBlank()) {
            _state.update { it.copy(errorMessage = "Todos los campos son obligatorios") }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isSaving = true, errorMessage = null) }
            val result = profileRepository.updateProfileInfo(
                ProfileInfo(
                    imageId = com.example.proyecto_movil.R.drawable.xocas,
                    username = current.nombreUsuario,
                    followers = 17,
                    following = 78
                )
            )
            _state.update {
                result.fold(
                    onSuccess = { it.copy(isSaving = false, isSaved = true) },
                    onFailure = { err -> it.copy(isSaving = false, errorMessage = err.message ?: "Error al guardar") }
                )
            }
        }
    }
}

