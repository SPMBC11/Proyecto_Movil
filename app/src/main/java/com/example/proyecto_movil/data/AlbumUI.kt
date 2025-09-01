package com.example.proyecto_movil.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumUI (
    val id: Int,
    val title: String,
    val year: String,
    @DrawableRes val coverRes: Int,
    val artist: ArtistUI
): Parcelable
