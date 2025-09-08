package com.example.proyecto_movil.ui.Screens.AddReview

import com.example.proyecto_movil.R

data class AddReviewState(
    val albumCoverRes: Int = R.drawable.circles,
    val albumTitle: String = "CIRCLES",
    val albumArtist: String = "Mac Miller",
    val albumYear: String = "2020",
    val dateString: String = "7 de Julio de 2025",
    val scorePercent: Int = 97,
    val liked: Boolean = false,
    val reviewText: String = "",
    val showMessage: Boolean = false,
    val errorMessage: String = "",
    val navigateCancel: Boolean = false,
    val navigatePublished: Boolean = false
)