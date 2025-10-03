package com.example.proyecto_movil.data.dtos

import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.UserUI

data class UserDto(
    val id: String,
    val username: String,
    val profile_pic: String?,
    val bio: String,
    val followers: Int,
    val following: Int,
    val createdAt: String,
    val updatedAt: String,

){
constructor(): this("", "", null, "", 0, 0, "", "")
}

data class ReviewDto(
    val id: String,
    val content: String,
    val score: Int,
    val is_low_score: Boolean,
    val album_id: String,
    val user_id: String,
    val createdAt: String, // en formato ISO // "2025-08-05T22:20:16.658Z"
    val updatedAt: String,
) {
    constructor() : this("", "", 0, false, "", "", "", "")
}
    fun ReviewDto.toReviewInfo(): ReviewInfo {
        return ReviewInfo(
            id = id,
            content = content,
            score = score,
            is_low_score = is_low_score,
            album_id = album_id,
            user_id = user_id,
            createdAt = createdAt,
            updatedAt = updatedAt,
            liked = false
        )
    }

