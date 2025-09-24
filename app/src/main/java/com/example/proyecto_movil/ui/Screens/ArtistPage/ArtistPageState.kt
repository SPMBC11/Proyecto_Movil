package com.example.proyecto_movil.ui.Screens.ArtistPage

import com.example.proyecto_movil.data.AlbumUI

data class ArtistPageState(
    val artistId: Int? = null,
    val artistName: String = "",
    val artistProfileRes: String = "", // URL o ruta local
    val followersText: String = "17K seguidores",
    val globalScoreText: String = "Puntaje global",
    val reviewsExtraText: String = "de 0 reseñas",
    val albums: List<AlbumUI> = emptyList(),

    // Navegación
    val navigateBack: Boolean = false,
    val openAlbumId: Int? = null,
    val navigateSeeAll: Boolean = false,

    // Mensajería (Snackbar)
    val showMessage: Boolean = false,
    val message: String? = null
)
