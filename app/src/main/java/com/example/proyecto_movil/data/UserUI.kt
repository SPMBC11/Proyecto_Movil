package com.example.proyecto_movil.data

import PlaylistUI
import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUI(
    val id: Int,
    val username: String,
    val profilePic: String,
    val bio: String = "",
    val followers: Int = 0,
    val following: Int = 0,
    val playlists: List<PlaylistUI> = emptyList()
) : Parcelable
