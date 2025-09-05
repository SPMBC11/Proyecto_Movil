package com.example.proyecto_movil.uiViews.profile

import com.example.proyecto_movil.uiViews.common.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.delay

class ProfileViewModel : BaseViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // TODO: conecta UserRepository
    // private val userRepo = UserRepository()
    // private val reviewRepo = ReviewRepository()

    fun onEvent(event: ProfileUiEvent) {
        when (event) {
            ProfileUiEvent.OnLoad -> load()
            ProfileUiEvent.OnRefresh -> refresh()
            is ProfileUiEvent.OnUpdateBio -> _uiState.update { it.copy(bio = event.value) }
            ProfileUiEvent.OnSaveProfile -> save()
            ProfileUiEvent.OnErrorConsumed -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun load() = launchSafely {
        _uiState.update { it.copy(isLoading = true, error = null) }
        try {
            delay(400)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    userName = "Usuario Demo",
                    bio = "Amante de la música",
                    reviewsCount = 12,
                    followers = 34,
                    following = 18
                )
            }
        } catch (t: Throwable) {
            _uiState.update { it.copy(isLoading = false, error = t.message ?: "Error cargando perfil") }
        }
    }

    private fun refresh() = launchSafely {
        _uiState.update { it.copy(isRefreshing = true) }
        try {
            delay(300)
            _uiState.update { it.copy(isRefreshing = false) }
        } catch (t: Throwable) {
            _uiState.update { it.copy(isRefreshing = false, error = t.message ?: "Error al refrescar") }
        }
    }

    private fun save() = launchSafely {
        try {
            delay(300)
            // userRepo.updateBio(uiState.value.bio)
        } catch (t: Throwable) {
            _uiState.update { it.copy(error = t.message ?: "No se pudo guardar el perfil") }
        }
    }
}