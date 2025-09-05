package com.example.proyecto_movil.uiViews.review.create

data class CreateReviewUiState(
    val title: String = "",
    val artist: String = "",
    val rating: Int = 0,
    val content: String = "",
    val isSubmitting: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)