package com.example.proyecto_movil.ui.Screens.AlbumReviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumReviewViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AlbumReviewState())
    val uiState: StateFlow<AlbumReviewState> = _uiState

    fun setAlbumById(id: Int) {
        viewModelScope.launch {
            _uiState.update { s -> s.copy(isLoading = true, showMessage = false, message = null) }
            try {
                val album = AlbumRepository.getAlbumById(id).getOrThrow()

                // Tus reseñas vienen con album/user/score (0..10)
                val reviews = ReviewRepository.getReviewsByAlbum(id).getOrElse { emptyList() }

                // Promedio a porcentaje (0..100)
                val avgPercent = if (reviews.isNotEmpty()) {
                    reviews.map { it.score }.average().let { (it * 10).toInt() }
                } else null

                _uiState.update { s ->
                    s.copy(
                        albumId = id,
                        albumCoverRes = album.coverRes,
                        albumTitle = album.title,
                        albumArtist = album.artist.name,
                        albumYear = album.year,
                        artistProfileRes = album.artist.profilePic,
                        reviews = reviews,
                        avgPercent = avgPercent,
                        isLoading = false,
                        showMessage = false,
                        message = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { s ->
                    s.copy(
                        isLoading = false,
                        showMessage = true,
                        message = e.message ?: "No se pudo cargar el álbum (id=$id)"
                    )
                }
            }
        }
    }

    // Navegación
    fun onArtistClicked()           { _uiState.update { s -> s.copy(navigateToArtist = true) } }
    fun consumeNavigateArtist()     { _uiState.update { s -> s.copy(navigateToArtist = false) } }
    fun onUserClicked(userId: Int)  { _uiState.update { s -> s.copy(openUserId = userId) } }
    fun consumeOpenUser()           { _uiState.update { s -> s.copy(openUserId = null) } }

    // Snackbar
    fun consumeMessage()            { _uiState.update { s -> s.copy(showMessage = false, message = null) } }
}
