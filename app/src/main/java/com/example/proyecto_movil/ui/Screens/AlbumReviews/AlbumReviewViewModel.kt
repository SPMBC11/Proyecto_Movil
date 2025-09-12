package com.example.proyecto_movil.ui.Screens.AlbumReviews

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AlbumReviewViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AlbumReviewState())
    val uiState: StateFlow<AlbumReviewState> = _uiState

    /**
     * Carga un álbum por su ID usando el repositorio.
     */
    fun setAlbumById(albumId: Int) {
        val result = AlbumRepository.getAlbumById(albumId)
        result
            .onSuccess { album ->
                val albumReviews = ReviewRepository.reviews.filter { it.album.id == album.id }
                val avg = if (albumReviews.isNotEmpty()) {
                    ((albumReviews.map { it.score }.average()) * 10).toInt()
                } else null

                _uiState.value = AlbumReviewState(
                    albumId = album.id,
                    albumCoverRes = album.coverRes,            // String (URL)
                    albumTitle = album.title,
                    albumArtist = album.artist.name,
                    albumYear = album.year,
                    artistProfileRes = album.artist.profilePic, // String (URL)
                    reviews = albumReviews,
                    avgPercent = avg
                )
            }
            .onFailure { e ->
                Log.e("AlbumReviewVM", "Error al cargar álbum $albumId", e)
                // Opcional: reflejar el error en el estado si agregas un campo errorMessage en AlbumReviewState
                _uiState.update { it.copy(/* errorMessage = e.message */) }
            }
    }

    fun onArtistClicked() = _uiState.update { it.copy(navigateToArtist = true) }
    fun consumeNavigateArtist() = _uiState.update { it.copy(navigateToArtist = false) }

    fun onUserClicked(userId: Int) = _uiState.update { it.copy(openUserId = userId) }
    fun consumeOpenUser() = _uiState.update { it.copy(openUserId = null) }
}
