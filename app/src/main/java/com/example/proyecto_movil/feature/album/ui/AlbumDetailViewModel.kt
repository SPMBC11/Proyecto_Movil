package com.example.proyecto_movil.feature.album.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.core.ServiceLocator
import com.example.proyecto_movil.feature.album.domain.model.Album
import kotlinx.coroutines.flow.*

data class AlbumDetailUiState(val loading: Boolean = true, val album: Album? = null)

class AlbumDetailViewModel(albumId: Int) : ViewModel() {
    private val repo = ServiceLocator.albumRepository
    val state: StateFlow<AlbumDetailUiState> =
        repo.getAlbumById(albumId)
            .map { AlbumDetailUiState(loading = false, album = it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AlbumDetailUiState())
}
