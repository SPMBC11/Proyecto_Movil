package com.example.proyecto_movil.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistUI(
    val id: Int,
    val name: String,
    @DrawableRes val profilePic: Int,
    val genre: String
) : Parcelable
