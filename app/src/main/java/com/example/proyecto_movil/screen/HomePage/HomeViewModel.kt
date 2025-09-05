package com.example.proyecto_movil.screen.HomePage

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.local.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Objeto(
val nombre: String)
@HiltViewModel
class HomeViewModel(): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init{
        _state.update {
            it.copy(albumList = AlbumRepository.albums)
        }
    }

}