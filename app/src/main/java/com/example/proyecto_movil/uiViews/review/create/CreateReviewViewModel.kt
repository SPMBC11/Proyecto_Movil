package com.example.proyecto_movil.uiViews.review.create

import com.example.proyecto_movil.uiViews.common.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.delay

class CreateReviewViewModel : BaseViewModel() {

    private val _uiState = MutableStateFlow(CreateReviewUiState())
    val uiState: StateFlow<CreateReviewUiState> = _uiState.asStateFlow()

    // TODO: conecta con tu ReviewRepository cuando definas DI
    // private val reviewRepo = ReviewRepository()

    fun onEvent(event: CreateReviewUiEvent) {
        when (event) {
            is CreateReviewUiEvent.OnTitleChange -> _uiState.update { it.copy(title = event.value, error = null) }
            is CreateReviewUiEvent.OnArtistChange -> _uiState.update { it.copy(artist = event.value, error = null) }
            is CreateReviewUiEvent.OnRatingChange -> _uiState.update { it.copy(rating = event.value, error = null) }
            is CreateReviewUiEvent.OnContentChange -> _uiState.update { it.copy(content = event.value, error = null) }
            CreateReviewUiEvent.OnSubmit -> submit()
            CreateReviewUiEvent.OnErrorConsumed -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun submit() = launchSafely {
        _uiState.update { it.copy(isSubmitting = true, error = null, success = false) }
        try {
            val s = uiState.value
            if (s.title.isBlank() || s.artist.isBlank() || s.rating <= 0) error("Completa título, artista y calificación")
            delay(500)
            // reviewRepo.addReview(...)
            _uiState.update { it.copy(isSubmitting = false, success = true) }
        } catch (t: Throwable) {
            _uiState.update { it.copy(isSubmitting = false, error = t.message ?: "No se pudo crear la reseña") }
        }
    }
}