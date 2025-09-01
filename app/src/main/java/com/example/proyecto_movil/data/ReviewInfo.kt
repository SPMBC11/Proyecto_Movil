package com.example.proyecto_movil.data

import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.UserUI

data class ReviewInfo(
    val album: AlbumUI,
    val user: UserUI,
    val content: String,
    val score: Double,
    val isLowScore: Boolean
)