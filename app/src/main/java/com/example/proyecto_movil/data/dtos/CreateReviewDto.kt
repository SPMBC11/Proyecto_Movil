package com.example.proyecto_movil.data.dtos

data class CreateReviewUserDto(
   val id: String,
   val username: String,
   val profile_pic: String,
)


data class CreateReviewDto(
    val content: String,
    val score: Int,
    val is_low_score: Boolean,
    val album_id: String,
    val user_id: String,
)
