package com.example.proyecto_movil.feature.album.domain.model

data class Artist(val id: Int, val name: String, val photoRes: Int)
data class Album(
    val id: Int,
    val title: String,
    val year: String,
    val coverRes: Int,
    val artist: Artist
)
