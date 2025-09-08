package com.example.proyecto_movil.ui.Screens.ArtistPage

import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI

data class ArtistPageState(
    val artistId: Int? = null,
    val artistName: String = "",
    val artistProfileRes: Int = R.drawable.logo,
    val followersText: String = "17K seguidores",
    val globalScoreText: String = "Puntaje global",
    val reviewsExtraText: String = "de 0 reseñas",
    val albums: List<AlbumUI> = emptyList(),

    val navigateBack: Boolean = false,
    val openAlbumId: Int? = null,
    val navigateSeeAll: Boolean = false
)