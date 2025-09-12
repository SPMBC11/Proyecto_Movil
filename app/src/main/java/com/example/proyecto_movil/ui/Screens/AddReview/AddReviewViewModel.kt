package com.example.proyecto_movil.ui.Screens.AddReview

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.AlbumUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class AddReviewViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AddReviewState())
    val uiState: StateFlow<AddReviewState> = _uiState

    /* ---------- Eventos de Navegación ---------- */
    fun onCancelClicked() =
        _uiState.update { it.copy(navigateCancel = true) }

    fun consumeCancel() =
        _uiState.update { it.copy(navigateCancel = false) }

    fun onPublishClicked() {
        val s = _uiState.value
        if (s.reviewText.isBlank()) {
            _uiState.update {
                it.copy(
                    showMessage = true,
                    errorMessage = "Escribe una reseña antes de publicar"
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    showMessage = false,
                    errorMessage = "",
                    navigatePublished = true
                )
            }
        }
    }

    fun consumePublished() =
        _uiState.update { it.copy(navigatePublished = false) }

    /* ---------- Actualizaciones de UI ---------- */
    fun updateReviewText(v: String) =
        _uiState.update { it.copy(reviewText = v) }

    fun toggleLike() =
        _uiState.update { it.copy(liked = !it.liked) }

    fun updateScore(score: Int) =
        _uiState.update { it.copy(scorePercent = score) }

    fun updateAlbum(album: AlbumUI) =
        _uiState.update {
            it.copy(
                albumTitle = album.title,
                albumArtist = album.artist.name,
                albumYear = album.year,
                albumCoverRes = album.coverRes
            )
        }

    fun onSettingsClicked() =
        _uiState.update { it.copy(navigateToSettings = true) }
}
