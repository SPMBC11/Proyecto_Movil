package com.example.proyecto_movil.data.dtos

import com.example.proyecto_movil.data.repository.UserProfileInfo

data class UserProfileDto(
    val id: Int,
    val name: String,
    val username: String,
    val profileImage: String,
    val followersCount: Int,
    val followingCount: Int,
    val bio: String,
    val createdAt: String,
    val updatedAt: String,
)
 fun UserProfileDto.toUserProfileInfo(): UserProfileInfo {
    return UserProfileInfo(
        id = id,toString(),
        username = username,
        name = name,
        bio = bio,
        location = location,
        website = website,
        profileImage = profileImage,
        birthDate = birthDate,
        followersCount = followersCount,
        followingCount = followingCount,
        bio = bio,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}