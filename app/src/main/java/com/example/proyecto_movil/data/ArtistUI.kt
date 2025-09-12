package com.example.proyecto_movil.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistUI(
    val id: Int,
    val name: String,
    val profilePic: String,
    val genre: String
) : Parcelable
