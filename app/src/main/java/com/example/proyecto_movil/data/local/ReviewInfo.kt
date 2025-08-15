package com.example.proyecto_movil.data.local

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.proyecto_movil.R

// Estructura
data class ReviewInfo(
    @DrawableRes val imageId: Int,
     val username: String,
     val content: String
)

