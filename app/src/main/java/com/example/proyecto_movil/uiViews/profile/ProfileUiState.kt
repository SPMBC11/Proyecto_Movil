package com.example.proyecto_movil.uiViews.profile

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val userName: String = "",
    val bio: String = "",
    val reviewsCount: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val isOwnProfile: Boolean = true,
    val error: String? = null
)