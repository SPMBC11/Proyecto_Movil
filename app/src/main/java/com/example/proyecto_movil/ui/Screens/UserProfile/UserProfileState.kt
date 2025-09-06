package com.example.proyecto_movil.ui.Screens.UserProfile

import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.ReviewInfo

data class UserProfileState(
    val username: String = "",
    val avatarRes: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val favoriteAlbums: List<AlbumUI> = emptyList(),
    val reviews: List<ReviewInfo> = emptyList(),
    val navigateBack: Boolean = false,
    val navigateToSettings: Boolean = false,
    val navigateToEditProfile: Boolean = false,
    val openAlbumId: Int? = null,
    val openReview: ReviewInfo? = null
)
