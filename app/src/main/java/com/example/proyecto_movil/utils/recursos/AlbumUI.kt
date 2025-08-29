package com.example.proyecto_movil.utils.recursos

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumUi(
    val id: Int,
    @DrawableRes val coverRes: Int,
    val title: String,
    val year: String,
    val artist: ArtistUI
) : Parcelable
