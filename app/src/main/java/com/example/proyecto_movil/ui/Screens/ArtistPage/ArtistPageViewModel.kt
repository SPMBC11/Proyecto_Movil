package com.example.proyecto_movil.ui.Screens.ArtistPage

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ArtistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ArtistPageViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArtistPageState())
    val uiState: StateFlow<ArtistPageState> = _uiState

    fun setArtist(artistId: Int?) {
        val artist = artistId?.let { id -> ArtistRepository.artists.find { it.id == id } }
            ?: ArtistRepository.artists.firstOrNull()
            ?: return

        val albums = AlbumRepository.albums.filter { it.artist.id == artist.id }

        val reviewsCount = 4500
        val globalScoreLabel = "Puntaje global"

        _uiState.update {
            it.copy(
                artistId = artist.id,
                artistName = artist.name,
                artistProfileRes = artist.profilePic,
                followersText = "17K seguidores",
                globalScoreText = globalScoreLabel,
                reviewsExtraText = "de $reviewsCount reseñas",
                albums = albums
            )
        }
    }

    fun onBackClicked() {
        _uiState.update { it.copy(navigateBack = true) }
    }
    fun consumeBack() {
        _uiState.update { it.copy(navigateBack = false) }
    }

    fun onSeeAllClicked() {
        _uiState.update { it.copy(navigateSeeAll = true) }
    }
    fun consumeSeeAll() {
        _uiState.update { it.copy(navigateSeeAll = false) }
    }

    fun onAlbumClicked(id: Int) {
        _uiState.update { it.copy(openAlbumId = id) }
    }
    fun consumeOpenAlbum() {
        _uiState.update { it.copy(openAlbumId = null) }
    }
}