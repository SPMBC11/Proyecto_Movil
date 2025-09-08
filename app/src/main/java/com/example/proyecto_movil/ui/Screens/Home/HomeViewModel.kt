package com.example.proyecto_movil.ui.Screens.Home

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.local.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(
        HomeState(
            albumList = AlbumRepository.albums.take(6)
        )
    )
    val state: StateFlow<HomeState> = _state

    fun updateSearchQuery(q: String) = _state.update { it.copy(searchQuery = q) }
}