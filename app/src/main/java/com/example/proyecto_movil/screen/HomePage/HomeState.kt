package com.example.proyecto_movil.screen.HomePage

import com.example.proyecto_movil.data.AlbumUI

data class HomeState(
    val albumList: List<AlbumUI> = emptyList()
)
