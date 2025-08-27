package com.example.proyecto_movil.data.local

import androidx.annotation.DrawableRes
import com.example.proyecto_movil.R

data class Artist(val id: Int, val name: String, @DrawableRes val photoRes: Int)
data class Album(val id: Int, val title: String, val year: String, @DrawableRes val coverRes: Int, val artistId: Int)

object Catalog {
    // 🔁 Ajusta con tus recursos reales
    val artists = listOf(
        Artist(1, "Sabrina Carpenter", R.drawable.sabrina),
        Artist(2, "Mac Miller",       R.drawable.mcmiller) // usa una foto si tienes otra
    )

    val albums = listOf(
        Album(101, "MAN`S BEST FRIEND", "2025", R.drawable.mansbestfriend, artistId = 1),
        Album(102, "SHORT N`SWEET",     "2024", R.drawable.shortnsweet,    artistId = 1),
        Album(103, "emails i can't send","2023",R.drawable.emailsicantsend,artistId = 1),
        Album(201, "Best of Mac",       "2018", R.drawable.mcmiller,       artistId = 2)
    )

    fun artistById(id: Int) = artists.find { it.id == id }
    fun albumById(id: Int)  = albums.find { it.id == id }
    fun albumsByArtist(artistId: Int) = albums.filter { it.artistId == artistId }
}
