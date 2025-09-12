package com.example.proyecto_movil.ui.Screens.Home

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeState(
            // antes: AlbumRepository.albums.take(6)
            albumList = AlbumRepository.getAlbums().getOrElse { emptyList() }.take(6)
        )
    )
    val uiState: StateFlow<HomeState> = _uiState

    fun updateSearchQuery(q: String) =
        _uiState.update { it.copy(searchQuery = q) }

    fun onProfileClicked() =
        _uiState.update { it.copy(navigateToProfile = true) }

    fun onSettingsClicked() =
        _uiState.update { it.copy(navigateToSettings = true) }

    fun onAlbumClicked(album: AlbumUI) =
        _uiState.update { it.copy(openAlbum = album) }

    // Consumers
    fun consumeProfile() =
        _uiState.update { it.copy(navigateToProfile = false) }

    fun consumeSettings() =
        _uiState.update { it.copy(navigateToSettings = false) }

    fun consumeOpenAlbum() =
        _uiState.update { it.copy(openAlbum = null) }

    fun getNewReleases(): List<AlbumUI> =
        AlbumRepository.getAlbums().getOrElse { emptyList() }
            .filter { it.id in listOf(601, 201, 501) }

    fun getPopularAlbums(): List<AlbumUI> =
        AlbumRepository.getAlbums().getOrElse { emptyList() }
            .filter { it.id in listOf(801, 701, 401) }
}
