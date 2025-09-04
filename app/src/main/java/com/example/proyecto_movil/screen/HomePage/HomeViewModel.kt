package com.example.proyecto_movil.screen.HomePage

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.local.AlbumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init{
        _state.update {
            it.copy(albumList = AlbumRepository.albums)
        }
    }

}