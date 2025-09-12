package com.example.proyecto_movil.ui.Screens.EditProfile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EditProfileState())
    val uiState: StateFlow<EditProfileState> = _uiState

    fun onBackClicked() = _uiState.update { it.copy(navigateBack = true) }
    fun consumeBack() = _uiState.update { it.copy(navigateBack = false) }

    fun onSaveClicked() {
        val s = _uiState.value
        if (s.nombrePersona.isBlank() || s.nombreUsuario.isBlank() || s.email.isBlank()) {
            _uiState.update { it.copy(showMessage = true, errorMessage = "Completa todos los campos") }
        } else {
            _uiState.update { it.copy(showMessage = false, errorMessage = "", navigateSaved = true) }
        }
    }
    fun consumeSaved() = _uiState.update { it.copy(navigateSaved = false) }

    fun updateNombrePersona(v: String) = _uiState.update { it.copy(nombrePersona = v) }
    fun updateNombreUsuario(v: String) = _uiState.update { it.copy(nombreUsuario = v) }
    fun updateEmail(v: String) = _uiState.update { it.copy(email = v) }
    fun updatePassword(v: String) = _uiState.update { it.copy(password = v) }
    fun toggleMostrarPassword() = _uiState.update { it.copy(mostrarPassword = !it.mostrarPassword) }

    fun updateAvatarUrl(url: String) = _uiState.update { it.copy(avatarUrl = url) }

    fun uploadAvatarUrlAfterPicker(downloadUrl: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(avatarUrl = downloadUrl) }
        }
    }
}
