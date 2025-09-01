package com.example.proyecto_movil.feature.album.domain.repository

import com.example.proyecto_movil.feature.album.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getAlbumById(id: Int): Flow<Album?>
    fun getAlbumsByArtist(artistId: Int): Flow<List<Album>>
    fun getFeatured(): Flow<List<Album>>
}
