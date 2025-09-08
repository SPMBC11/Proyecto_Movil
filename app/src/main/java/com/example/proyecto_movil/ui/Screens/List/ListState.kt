package com.example.proyecto_movil.ui.Screens.Lists

import com.example.proyecto_movil.R

data class AlbumItem(
    val id: Int,
    val coverRes: Int,
    val title: String,
    val year: String
)

data class ListState(
    val title: String = "Lista",
    val description: String = "",
    val creatorName: String = "",
    val creatorAvatarRes: Int = 0,
    val likes: Int = 0,
    val listenPercent: String = "0%",
    val albums: List<AlbumItem> = emptyList(),
    val navigateBack: Boolean = false,
    val navigateToSettings: Boolean = false,
    val openAlbumId: Int? = null,
    val playAlbumId: Int? = null
)
