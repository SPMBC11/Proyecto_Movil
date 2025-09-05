package com.example.proyecto_movil.core

import com.example.proyecto_movil.feature.album.data.AlbumRepositoryImpl
import com.example.proyecto_movil.feature.album.domain.repository.AlbumRepository
import com.example.proyecto_movil.feature.reviews.data.ReviewRepositoryImpl
import com.example.proyecto_movil.feature.reviews.domain.repository.ReviewRepository
import com.example.proyecto_movil.feature.auth.data.FakeAuthRepository
import com.example.proyecto_movil.feature.auth.domain.repository.AuthRepository
import com.example.proyecto_movil.feature.profile.data.ProfileRepositoryImpl
import com.example.proyecto_movil.feature.profile.domain.repository.ProfileRepository

object ServiceLocator {
    val albumRepository: AlbumRepository by lazy { AlbumRepositoryImpl() }
    val reviewRepository: ReviewRepository by lazy { ReviewRepositoryImpl() }
    val authRepository: AuthRepository by lazy { FakeAuthRepository() }
    val profileRepository: ProfileRepository by lazy { ProfileRepositoryImpl() }
}
