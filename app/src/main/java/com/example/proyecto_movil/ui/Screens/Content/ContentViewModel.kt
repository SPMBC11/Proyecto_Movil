package com.example.proyecto_movil.ui.Screens.Content

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ContentState())
    val uiState: StateFlow<ContentState> = _uiState

    /** Llama esto al entrar a la pantalla (o desde el NavHost) */
    // ContentViewModel.kt
    fun setInitial(artistId: Int? = null, isOwner: Boolean = false) {
        // 1) Resolver nombre de encabezado con Result
        val header = artistId?.let { id ->
            ArtistRepository.getArtistById(id).getOrNull()?.name ?: "Contenido"
        } ?: "Contenido"

        // 2) Tomar álbumes con Result
        val allAlbums = AlbumRepository.getAlbums().getOrElse { emptyList() }
        val albums = artistId?.let { id ->
            allAlbums.filter { it.artist.id == id }
        } ?: allAlbums

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