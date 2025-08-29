package com.example.proyecto_movil.utils.recursos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistUI(
    val id: Int,
    val name: String,
    val genre: String,
    val displayName: String
) : Parcelable
