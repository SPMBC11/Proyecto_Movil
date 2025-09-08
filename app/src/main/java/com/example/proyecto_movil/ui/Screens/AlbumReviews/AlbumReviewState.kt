package com.example.proyecto_movil.ui.Screens.AlbumReviews

import com.example.proyecto_movil.data.ReviewInfo

data class AlbumReviewState(
    val albumId: Int = 0,
    val albumCoverRes: Int = 0,
    val albumTitle: String = "",
    val albumArtist: String = "",
    val albumYear: String = "",
    val artistProfileRes: Int = 0,
    val reviews: List<ReviewInfo> = emptyList(),
    val avgPercent: Int? = null,
    val navigateToArtist: Boolean = false,
    val openUserId: Int? = null
)