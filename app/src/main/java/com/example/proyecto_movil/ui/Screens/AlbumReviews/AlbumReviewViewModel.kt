package com.example.proyecto_movil.ui.Screens.AlbumReviews

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.AlbumUI
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

    fun setAlbum(album: AlbumUI) {
        val albumReviews = ReviewRepository.reviews.filter { it.album.id == album.id }
        val avg = if (albumReviews.isNotEmpty()) {
            ((albumReviews.map { it.score }.average()) * 10).toInt()
        } else null
        _uiState.value = AlbumReviewState(
            albumId = album.id,
            albumCoverRes = album.coverRes,
            albumTitle = album.title,
            albumArtist = album.artist.name,
            albumYear = album.year,
            artistProfileRes = album.artist.profilePic,
            reviews = albumReviews,
            avgPercent = avg
        )
    }

    fun onArtistClicked() = _uiState.update { it.copy(navigateToArtist = true) }
    fun consumeNavigateArtist() = _uiState.update { it.copy(navigateToArtist = false) }

    fun onUserClicked(userId: Int) = _uiState.update { it.copy(openUserId = userId) }
    fun consumeOpenUser() = _uiState.update { it.copy(openUserId = null) }
}