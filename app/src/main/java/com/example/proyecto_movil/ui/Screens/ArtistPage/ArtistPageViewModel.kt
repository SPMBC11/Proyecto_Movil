package com.example.proyecto_movil.ui.Screens.ArtistPage

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ArtistPageViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(ArtistPageState())
    val uiState: StateFlow<ArtistPageState> = _uiState

    fun setArtist(artistId: Int?) {
        // 1) Resolver artista con Result
        val artist = if (artistId != null) {
            ArtistRepository.getArtistById(artistId).getOrNull()
        } else {
            ArtistRepository.getArtists().getOrNull()?.firstOrNull()
        }

        if (artist == null) {
            Log.e("ArtistPageVM", "No se pudo cargar el artista (artistId=$artistId)")
            return
        }

        // 2) Traer álbumes con Result y filtrar por artista
        val albums = AlbumRepository
            .getAlbums()
            .getOrElse { emptyList() }
            .filter { it.artist.id == artist.id }

        // 3) Datos de UI fijos/demo (puedes conectarlo a repos si lo deseas)
        val reviewsCount = 4500
        val globalScoreLabel = "Puntaje global"

        _uiState.update {
            it.copy(
                artistId = artist.id,
                artistName = artist.name,
                artistProfileRes = artist.profilePic,   // String (URL)
                followersText = "17K seguidores",
                globalScoreText = globalScoreLabel,
                reviewsExtraText = "de $reviewsCount reseñas",
                albums = albums
            )
        }
    }

    fun onBackClicked() { _uiState.update { it.copy(navigateBack = true) } }
    fun consumeBack() { _uiState.update { it.copy(navigateBack = false) } }

    fun onSeeAllClicked() { _uiState.update { it.copy(navigateSeeAll = true) } }
    fun consumeSeeAll() { _uiState.update { it.copy(navigateSeeAll = false) } }

    fun onAlbumClicked(id: Int) { _uiState.update { it.copy(openAlbumId = id) } }
    fun consumeOpenAlbum() { _uiState.update { it.copy(openAlbumId = null) } }
}
