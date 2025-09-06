package com.example.proyecto_movil.ui.Screens.Content

import com.example.proyecto_movil.data.AlbumUI

data class ContentState(
    val artistId: Int? = null,
    val isOwner: Boolean = false,
    val headerTitle: String = "Contenido",
    val albums: List<AlbumUI> = emptyList(),

    // Navegación/intents
    val navigateBack: Boolean = false,
    val openAlbumId: Int? = null,
    val seeAllDiscography: Boolean = false,
    val editAlbumId: Int? = null
)