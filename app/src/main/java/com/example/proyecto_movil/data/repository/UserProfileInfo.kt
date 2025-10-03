package com.example.proyecto_movil.data.repository

data class UserProfileInfo(
    val id: String,
    val username: String,
    val profileImage: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val createdAt: String,
    val updatedAt: String,
){
constructor(): this("", "", "", "", 0, 0, "", "")
}
