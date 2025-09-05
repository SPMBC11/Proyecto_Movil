package com.example.proyecto_movil.feature.profile.domain.repository

import com.example.proyecto_movil.data.local.AlbumProfInfo
import com.example.proyecto_movil.data.local.ProfileInfo
import com.example.proyecto_movil.data.local.ReviewProfInfo
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfileInfo(): Flow<ProfileInfo>
    fun getFavoriteAlbums(): Flow<List<AlbumProfInfo>>
    fun getUserReviews(): Flow<List<ReviewProfInfo>>
    suspend fun updateProfileInfo(profileInfo: ProfileInfo): Result<Unit>
}

