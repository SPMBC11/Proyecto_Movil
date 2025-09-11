package com.example.proyecto_movil.ui.Screens.AddReview

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddReviewViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AddReviewState())
    val uiState: StateFlow<AddReviewState> = _uiState

    fun onCancelClicked() = _uiState.update { it.copy(navigateCancel = true) }
    fun consumeCancel() = _uiState.update { it.copy(navigateCancel = false) }

    fun onPublishClicked() {
        val s = _uiState.value
        if (s.reviewText.isBlank()) {
            _uiState.update { it.copy(showMessage = true, errorMessage = "Escribe una reseña antes de publicar") }
        } else {
            _uiState.update { it.copy(showMessage = false, errorMessage = "", navigatePublished = true) }
        }
    }
    fun consumePublished() = _uiState.update { it.copy(navigatePublished = false) }

    fun updateReviewText(v: String) = _uiState.update { it.copy(reviewText = v) }
    fun toggleLike() = _uiState.update { it.copy(liked = !it.liked) }

    fun onSettingsClicked() = _uiState.update { s -> s.copy(navigateToSettings = true) }
}