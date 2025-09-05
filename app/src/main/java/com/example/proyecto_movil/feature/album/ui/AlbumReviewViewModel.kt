package com.example.proyecto_movil.feature.album.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.feature.album.domain.model.Album
import com.example.proyecto_movil.feature.album.domain.repository.AlbumRepository
import com.example.proyecto_movil.feature.reviews.domain.model.Review
import com.example.proyecto_movil.feature.reviews.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AlbumReviewUiState(
    val isLoading: Boolean = true,
    val album: Album? = null,
    val reviews: List<Review> = emptyList(),
    val error: String? = null
)

class AlbumReviewViewModel(
    private val albumRepository: AlbumRepository,
    private val reviewRepository: ReviewRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AlbumReviewUiState())
    val state: StateFlow<AlbumReviewUiState> = _state.asStateFlow()

    init {
        val albumId: Int? = savedStateHandle.get<Int>("albumId")
        if (albumId == null) {
            _state.update { it.copy(isLoading = false, error = "Falta albumId en la navegación") }
            return
        }

        viewModelScope.launch {
            val albumFlow = albumRepository.getAlbumById(albumId)
            val reviewsFlow = reviewRepository.getReviewsForAlbum(albumId)

            combine(albumFlow, reviewsFlow) { album, reviews ->
                album to reviews
            }.collect { (album, reviews) ->
                if (album == null) {
                    _state.update { it.copy(isLoading = false, error = "Álbum no encontrado") }
                } else {
                    _state.update { it.copy(isLoading = false, album = album, reviews = reviews) }
                }
            }
        }
    }
}

