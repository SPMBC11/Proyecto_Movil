package com.example.proyecto_movil.data.dtos

data class CreateReviewUserDto(
    val name: String? = null,
    val username: String? = null,
    val profileImage: String? = null
)


data class CreateReviewDto(
    val content: String,
    var userId: Int,
    val parentReviewId: Int?,
    val reviewId: String? = null,
    val user: CreateReviewUserDto? = null
)
