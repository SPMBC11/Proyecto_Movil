package com.example.proyecto_movil.uiViews.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.data.local.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumReviewViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AlbumReviewUiState())
    val uiState: StateFlow<AlbumReviewUiState> = _uiState

    fun onEvent(event: AlbumReviewUiEvent) {
        when (event) {
            is AlbumReviewUiEvent.OnLoad -> loadAlbumReviews(event.albumId)
        }
    }

    private fun loadAlbumReviews(albumId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val reviews = ReviewRepository.reviews.filter { it.album.id == albumId }
                val avg = if (reviews.isNotEmpty()) reviews.map { it.score }.average().toFloat() else 0f
                _uiState.update { it.copy(isLoading = false, reviews = reviews, averageRating = avg) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}
