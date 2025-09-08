package com.example.proyecto_movil.ui.Screens.Content

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ArtistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ContentViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ContentState())
    val uiState: StateFlow<ContentState> = _uiState

    /** Llama esto al entrar a la pantalla (o desde el NavHost) */
    fun setInitial(artistId: Int? = null, isOwner: Boolean = false) {
        val header = artistId?.let { id ->
            ArtistRepository.artists.find { it.id == id }?.name ?: "Contenido"
        } ?: "Contenido"

        val albums = artistId?.let { id ->
            AlbumRepository.albums.filter { it.artist.id == id }
        } ?: AlbumRepository.albums

        _uiState.update {
            it.copy(
                artistId = artistId,
                isOwner = isOwner,
                headerTitle = header,
                albums = albums
            )
        }
    }

    // Acciones de UI
    fun onBackClicked() = _uiState.update { it.copy(navigateBack = true) }
    fun consumeBack() = _uiState.update { it.copy(navigateBack = false) }

    fun onOpenAlbum(id: Int) = _uiState.update { it.copy(openAlbumId = id) }
    fun consumeOpenAlbum() = _uiState.update { it.copy(openAlbumId = null) }

    fun onSeeAllClicked() = _uiState.update { it.copy(seeAllDiscography = true) }
    fun consumeSeeAll() = _uiState.update { it.copy(seeAllDiscography = false) }

    fun onEditAlbumClicked(id: Int) = _uiState.update { it.copy(editAlbumId = id) }
    fun consumeEditAlbum() = _uiState.update { it.copy(editAlbumId = null) }
}