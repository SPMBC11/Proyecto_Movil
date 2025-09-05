package com.example.proyecto_movil.uiViews.review.detail

data class ReviewDetailUiState(
    val isLoading: Boolean = false,
    val reviewId: String? = null,
    val title: String = "",
    val artist: String = "",
    val rating: Int = 0,
    val content: String = "",
    val liked: Boolean = false,
    val error: String? = null
)