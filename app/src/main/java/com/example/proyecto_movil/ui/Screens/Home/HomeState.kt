package com.example.proyecto_movil.ui.Screens.Home

import com.example.proyecto_movil.data.AlbumUI

data class HomeState(
    val albumList: List<AlbumUI> = emptyList(),
    val searchQuery: String = ""
)
