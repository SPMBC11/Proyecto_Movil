package com.example.proyecto_movil.ui.Screens.AlbumReviews

import com.example.proyecto_movil.data.ReviewInfo

data class AlbumReviewState(
    val albumId: Int = 0,
    val albumCoverRes: String = "",
    val albumTitle: String = "",
    val albumArtist: String = "",
    val albumYear: String = "",
    val artistProfileRes: String = "",
    val reviews: List<ReviewInfo> = emptyList(),
    val avgPercent: Int? = null,
    val navigateToArtist: Boolean = false,
    val openUserId: Int? = null
)