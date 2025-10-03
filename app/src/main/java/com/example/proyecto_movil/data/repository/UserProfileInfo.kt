package com.example.proyecto_movil.data.repository

data class UserProfileInfo(
    val backgroundImage: String? = null,
    val profileImage: String? = null,
    val name: String,
    val username: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val location: String,
    val website: String,
    val birthDate: String,
    val accountCreationDate: String,
    val id: String,
    val followed: Boolean = false
){

    constructor() : this (
        backgroundImage = null,
        profileImage = null,
        name = "",
        username = "",
        bio = "",
        followers = 0,
        following = 0,
        location = "",
        website = "",
        birthDate = "",
        accountCreationDate = "",
        id = "",
    )
}
