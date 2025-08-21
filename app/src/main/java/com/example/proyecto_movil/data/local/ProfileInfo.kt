package com.example.proyecto_movil.data.local

import androidx.annotation.DrawableRes

data class ProfileInfo(
    @DrawableRes val imageId: Int,
    val username: String,
    val followers: Int,
    val following: Int
)

data class AlbumProfInfo(
    @DrawableRes val imageId: Int,
    val title: String,
    val artist: String
)

data class ReviewProfInfo(
    @DrawableRes val imageId: Int,
    val title: String,
    val artist: String,
    val score: String,
    val isLowScore: Boolean = false
)