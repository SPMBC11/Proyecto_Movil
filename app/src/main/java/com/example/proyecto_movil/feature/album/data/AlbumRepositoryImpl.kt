package com.example.proyecto_movil.feature.album.data

import com.example.proyecto_movil.data.local.Catalog
import com.example.proyecto_movil.feature.album.domain.model.Album
import com.example.proyecto_movil.feature.album.domain.model.Artist
import com.example.proyecto_movil.feature.album.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AlbumRepositoryImpl : AlbumRepository {
    override fun getAlbumById(id: Int): Flow<Album?> = flow {
        val a = Catalog.albumById(id)
        emit(a?.let {
            Album(
                id = it.id, title = it.title, year = it.year, coverRes = it.coverRes,
                artist = Catalog.artistById(it.artistId)?.let { ar -> Artist(ar.id, ar.name, ar.photoRes) }
                    ?: Artist(0, it.artistName, 0)
            )
        })
    }
    override fun getAlbumsByArtist(artistId: Int): Flow<List<Album>> = flow {
        emit(Catalog.albumsByArtist(artistId).map { alb ->
            Album(
                id = alb.id, title = alb.title, year = alb.year, coverRes = alb.coverRes,
                artist = Catalog.artistById(alb.artistId)?.let { ar -> Artist(ar.id, ar.name, ar.photoRes) }
                    ?: Artist(0, alb.artistName, 0)
            )
        })
    }
    override fun getFeatured(): Flow<List<Album>> = flow {
        emit(Catalog.albums.map { alb ->
            Album(
                id = alb.id, title = alb.title, year = alb.year, coverRes = alb.coverRes,
                artist = Catalog.artistById(alb.artistId)?.let { ar -> Artist(ar.id, ar.name, ar.photoRes) }
                    ?: Artist(0, alb.artistName, 0)
            )
        })
    }
}
