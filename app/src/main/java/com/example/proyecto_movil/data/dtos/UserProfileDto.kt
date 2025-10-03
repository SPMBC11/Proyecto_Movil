package com.example.proyecto_movil.data.dtos

import com.example.proyecto_movil.data.repository.UserProfileInfo

data class UserProfileDto(
    val id: Int,
    val username: String,
    val profile_pic: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val createdAt: String,
    val updatedAt: String,
)
 fun UserProfileDto.toUserProfileInfo(): UserProfileInfo {
    return UserProfileInfo(
        id = id,
        username = username,
        profileImage = profile_pic,
        bio = bio,
        followers = followers,
        following = following,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}