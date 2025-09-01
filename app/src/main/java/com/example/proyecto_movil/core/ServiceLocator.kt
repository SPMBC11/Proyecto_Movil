package com.example.proyecto_movil.core

import com.example.proyecto_movil.feature.album.data.AlbumRepositoryImpl
import com.example.proyecto_movil.feature.album.domain.repository.AlbumRepository
import com.example.proyecto_movil.feature.reviews.data.ReviewRepositoryImpl
import com.example.proyecto_movil.feature.reviews.domain.repository.ReviewRepository

object ServiceLocator {
    val albumRepository: AlbumRepository by lazy { AlbumRepositoryImpl() }
    val reviewRepository: ReviewRepository by lazy { ReviewRepositoryImpl() }
}
