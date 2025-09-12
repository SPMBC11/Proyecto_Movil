package com.example.proyecto_movil.data.local

import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.ArtistUI

object ArtistRepository {

public  val artists = listOf(
        ArtistUI(1, "Sabrina Carpenter", "https://i.scdn.co/image/ab6761610000e5eb78e45cfa4697ce3c437cb455", genre = "Pop"),
        ArtistUI(2, "Mac Miller", "https://i.scdn.co/image/ab6761610000e5ebed3b89aa602145fde71a163a", genre = "Hip Hop"),
        ArtistUI(3, "Aminé", "https://i.scdn.co/image/ab6761610000e5eb31ad986e8e1328db4b3d83cd", genre = "Hip Hop"),
        ArtistUI(4, "Drake", "https://i.scdn.co/image/ab6761610000e5eb4293385d324db8558179afd9", genre = "Hip Hop"),
        ArtistUI(5, "Feid", "https://i.scdn.co/image/ab6761610000e5eb600ee3d2a14da8d038fa7bbf", genre = "Reggaeton"),
        ArtistUI(6, "Tyler, The Creator", "https://i.scdn.co/image/ab67616d0000b273124e9249fada4ff3c3a0739c", genre = "Pop"),
        ArtistUI(7, "Billie Eilish", "https://i.scdn.co/image/ab67616d00001e022a038d3bf875d23e4aeaa84e", genre = "Pop"),
        ArtistUI(8, "Bad Bunny", "https://i.scdn.co/image/ab6761610000e5eb81f47f44084e0a09b5f0fa13", genre = "Reggaeton"),
        ArtistUI(9, "Andrés Cepeda", "https://i.scdn.co/image/ab67616d0000b273b020287dabb730a9f8b239bf", genre = "Balada"),
        ArtistUI(10, "AKRIILA", "https://i.scdn.co/image/ab67616d0000b273f3b8bac4ec47a6fb1fa626da", genre = "Hip Hop"),
        ArtistUI(11, "Danny Ocean", "https://i.scdn.co/image/ab6761610000e5ebb485cde5cba6de6e5685278a", genre = "Pop"),
        ArtistUI(12, "Gipsy Kings", "https://i.scdn.co/image/ab67616d00001e02fcc39dfc2293924d2f0f7e51", "Pop Flamenco"),
        ArtistUI(13, "Abraham Mateo", "https://i.scdn.co/image/ab6761610000e5ebc37b5aff82743fd506ae3f47", "Pop"),
        ArtistUI(14, "Kendrick Lamar", "https://i.scdn.co/image/ab6761610000e5eb39ba6dcd4355c03de0b50918", genre = "Hip Hop"),
        ArtistUI(15, "Shakira", "https://i.scdn.co/image/ab6761610000e5eb2528c726e5ddb90a7197e527", genre = "Pop"),
        ArtistUI(16, "Binomio de Oro", "https://i.scdn.co/image/ab67616d00001e02ae20aa6c6aa8fc9f86ac2b58", genre = "Vallenato"),
        ArtistUI(17, "Mr. Black", "https://i.scdn.co/image/ab67616d0000b27358e9506c16c420f4ac187f21", genre = "Champeta"),
        ArtistUI(18, "LMFAO", "https://i.scdn.co/image/2d75f5222d8a92d5e12419ae3e5238261f943df6", genre = "EDM"),
        ArtistUI(19, "Justin Bieber", "https://i.scdn.co/image/ab67616d0000b273f46b9d202509a8f7384b90de", genre = "Pop"),
        ArtistUI(20, "David Lynch", "https://i.scdn.co/image/95aab2d79f7db9b6da2ea2e04a552ad84a9f88a1", genre = "Hell"),
    )
    fun getArtists(): Result<List<ArtistUI>> = try {
        Result.success(artists)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getArtistById(id: Int): Result<ArtistUI> = try {
        val artist = artists.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Artista con id $id no encontrado")
        Result.success(artist)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
