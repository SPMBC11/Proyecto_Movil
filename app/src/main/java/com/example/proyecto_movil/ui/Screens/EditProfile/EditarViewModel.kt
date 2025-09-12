package com.example.proyecto_movil.ui.Screens.EditProfile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EditProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileState())
    val uiState: StateFlow<EditProfileState> = _uiState

    fun onBackClicked() = _uiState.update { it.copy(navigateBack = true) }
    fun consumeBack() = _uiState.update { it.copy(navigateBack = false) }

    fun onSaveClicked() = _uiState.update { it.copy(showMessage = false, errorMessage = "", navigateSaved = true) }
    fun consumeSaved() = _uiState.update { it.copy(navigateSaved = false) }

    fun updateNombrePersona(v: String) = _uiState.update { it.copy(nombrePersona = v) }
    fun updateNombreUsuario(v: String) = _uiState.update { it.copy(nombreUsuario = v) }
    fun updateEmail(v: String) = _uiState.update { it.copy(email = v) }
    fun updatePassword(v: String) = _uiState.update { it.copy(password = v) }
    fun toggleMostrarPassword() = _uiState.update { it.copy(mostrarPassword = !it.mostrarPassword) }

    fun updateAvatarUrlManually(url: String) = _uiState.update { it.copy(avatarUrl = url) }

    /** Sube una imagen a Storage a partir de un URI del picker y guarda el downloadUrl en el estado */
    fun uploadAvatarFromPicker(uri: Uri) {
        viewModelScope.launch {
            _uiState.update { it.copy(isUploading = true, errorMessage = "") }
            try {
                val uid = FirebaseAuth.getInstance().currentUser?.uid ?: "anonymous"
                val fileName = "avatar-${System.currentTimeMillis()}.jpg"
                val ref = FirebaseStorage.getInstance().reference.child("users/$uid/$fileName")

                // Subir archivo desde URI
                ref.putFile(uri).await()

                // Obtener URL de descarga pública
                val downloadUrl = ref.downloadUrl.await().toString()
                _uiState.update { it.copy(avatarUrl = downloadUrl, isUploading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isUploading = false, errorMessage = e.message ?: "Error al subir la imagen")
                }
            }
        }
    }
}
