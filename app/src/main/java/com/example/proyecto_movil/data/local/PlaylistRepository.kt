package com.example.proyecto_movil.data.local

import PlaylistUI

object PlaylistRepository {
    val playlists = listOf(
        PlaylistUI(
            id = 101,
            title = "Clásicos del Rap",
            description = "Lo mejor de los 2000s",
            albums = AlbumRepository.albums.filter { it.artist.genre == "Hip Hop" }
        ),
        PlaylistUI(
            id = 102,
            title = "Pop para trabajar",
            description = "Lo mejor del pop actual",
            albums = AlbumRepository.albums.filter { it.artist.genre == "Pop" }
        ),
        PlaylistUI(
            id = 103,
            title = "Mix Latino",
            description = "Reggaetón, champeta y balada para toda ocasión",
            albums = AlbumRepository.albums.filter { it.artist.genre in listOf("Reggaeton", "Balada", "Champeta") }
        ),
        PlaylistUI(
            id = 104,
            title = "EDM Party",
            description = "Electro y beats para encender la fiesta 🎉",
            albums = AlbumRepository.albums.filter { it.artist.genre == "EDM" }
        ),
        PlaylistUI(
            id = 105,
            title = "Experimental & Cine",
            description = "Soundtracks y rarezas",
            albums = AlbumRepository.albums.filter { it.artist.name == "David Lynch" }
        )
    )

    fun getPlaylistById(id: Int): PlaylistUI? = playlists.find { it.id == id }
}
