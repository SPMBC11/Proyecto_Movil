package com.example.proyecto_movil.data.dtos

import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.UserUI

data class UserDto(
    val id: String,
    val username: String,
    val profile_pic: String?
    val bio: String,
    val followers: Int,
    val following: Int,
    val createdAt: String,
    val updatedAt: String,

){
    constructor(): this("", "", "", "")
}

data class ReviewDto(
    val userId: String,
    val id: String,
    val imageUrl: String?, // null por ahora
    val content: String,
    val likesCount: Int,

    val comments: Int,
    val createdAt: String, // en formato ISO // "2025-08-05T22:20:16.658Z"
    val updatedAt: String,
    val user: UserDto,
    //nuevo
    var liked: Boolean = false
) {
    constructor() : this("", "", null, "", 0, 0, "", "", UserDto("", "", "", null), false)

    fun ReviewDto.toReviewInfo(): ReviewInfo {
        return ReviewInfo(
            profileImage = user.profileImage ?: "",
            name = user.name,
            username = user.username,
            content = content,
            time = createdAt,//fechaCreacion
            comments = comments,
            likes = likesCount,
            id = id,
            userId = userId,
            liked = liked
        )
    }
}
