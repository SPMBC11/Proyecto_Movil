package com.example.proyecto_movil.feature.reviews.domain.model

data class Review(
    val userImageRes: Int,
    val userName: String,
    val content: String,
    val liked: Boolean = true
)
