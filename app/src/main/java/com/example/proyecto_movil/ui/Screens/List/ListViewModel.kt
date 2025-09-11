package com.example.proyecto_movil.ui.Screens.Lists

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(
        ListState(
            title = "Lista",
            description = "Pero la quería tanto que daba igual, porque cinco minutos con ella significaban diez horas con cualquier otra...",
            creatorName = "el.xokas",
            creatorAvatarRes = R.drawable.xocas,
            likes = 0,
            listenPercent = "100%",
            albums = listOf(
                AlbumItem(1, R.drawable.dosmil, "2000", "2000"),
                AlbumItem(2, R.drawable.presidente_champeta, "El presidente de la Champeta", "2015"),
                AlbumItem(3, R.drawable.goodforyou, "GOODFORYOU", "2023"),
                AlbumItem(4, R.drawable.party_rock, "Party Rock", "2011")
            )
        )
    )
    val uiState: StateFlow<ListState> = _uiState

    fun onBackClicked() = _uiState.update { s -> s.copy(navigateBack = true) }
    fun onSettingsClicked() = _uiState.update { s -> s.copy(navigateToSettings = true) }
    fun onAlbumClicked(id: Int) = _uiState.update { s -> s.copy(openAlbumId = id) }
    fun onPlayClicked(id: Int) = _uiState.update { s -> s.copy(playAlbumId = id) }

    fun consumeBack() = _uiState.update { s -> s.copy(navigateBack = false) }
    fun consumeSettings() = _uiState.update { s -> s.copy(navigateToSettings = false) }
    fun consumeOpenAlbum() = _uiState.update { s -> s.copy(openAlbumId = null) }
    fun consumePlayAlbum() = _uiState.update { s -> s.copy(playAlbumId = null) }
}
