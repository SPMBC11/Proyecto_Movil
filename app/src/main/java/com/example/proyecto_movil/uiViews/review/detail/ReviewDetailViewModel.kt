package com.example.proyecto_movil.uiViews.review.detail

import com.example.proyecto_movil.uiViews.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.delay
@HiltViewModel
class ReviewDetailViewModel : BaseViewModel() {

    private val _uiState = MutableStateFlow(ReviewDetailUiState())
    val uiState: StateFlow<ReviewDetailUiState> = _uiState.asStateFlow()

    // TODO: conecta ReviewRepository
    // private val reviewRepo = ReviewRepository()

    fun onEvent(event: ReviewDetailUiEvent) {
        when (event) {
            is ReviewDetailUiEvent.OnLoad -> load(event.id)
            ReviewDetailUiEvent.OnToggleLike -> toggleLike()
            ReviewDetailUiEvent.OnRetry -> uiState.value.reviewId?.let { load(it) }
            ReviewDetailUiEvent.OnErrorConsumed -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun load(id: String) = launchSafely {
        _uiState.update { it.copy(isLoading = true, error = null, reviewId = id) }
        try {
            delay(400)
            // val review = reviewRepo.getById(id)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    title = "Demo Review $id",
                    artist = "Artista X",
                    rating = 4,
                    content = "Contenido de ejemplo...",
                    liked = false
                )
            }
        } catch (t: Throwable) {
            _uiState.update { it.copy(isLoading = false, error = t.message ?: "Error cargando detalle") }
        }
    }

    private fun toggleLike() = launchSafely {
        val liked = !uiState.value.liked
        _uiState.update { it.copy(liked = liked) }
        try {
            delay(250)
            // reviewRepo.toggleLike(uiState.value.reviewId!!, liked)
        } catch (t: Throwable) {
            _uiState.update { it.copy(liked = !liked, error = "No se pudo actualizar el like") }
        }
    }
}