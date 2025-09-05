package com.example.proyecto_movil.uiViews.album

import com.example.proyecto_movil.data.ReviewInfo

data class AlbumReviewUiState(
    val isLoading: Boolean = false,
    val reviews: List<ReviewInfo> = emptyList(),
    val averageRating: Float = 0f,
    val error: String? = null
)