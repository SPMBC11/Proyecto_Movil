package com.example.proyecto_movil.feature.profile.data

import com.example.proyecto_movil.data.local.AlbumProfInfo
import com.example.proyecto_movil.data.local.FalseAlbumProfRepository
import com.example.proyecto_movil.data.local.FalseProfileInfoRepository
import com.example.proyecto_movil.data.local.FalseReviewProfRepository
import com.example.proyecto_movil.data.local.ProfileInfo
import com.example.proyecto_movil.data.local.ReviewProfInfo
import com.example.proyecto_movil.feature.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileRepositoryImpl : ProfileRepository {
    private val profileFlow = MutableStateFlow(FalseProfileInfoRepository.profile)
    private val albumsFlow = MutableStateFlow(FalseAlbumProfRepository.albums)
    private val reviewsFlow = MutableStateFlow(FalseReviewProfRepository.reviews)

    override fun getProfileInfo(): Flow<ProfileInfo> = profileFlow.asStateFlow()

    override fun getFavoriteAlbums(): Flow<List<AlbumProfInfo>> = albumsFlow.asStateFlow()

    override fun getUserReviews(): Flow<List<ReviewProfInfo>> = reviewsFlow.asStateFlow()

    override suspend fun updateProfileInfo(profileInfo: ProfileInfo): Result<Unit> {
        profileFlow.value = profileInfo
        return Result.success(Unit)
    }
}

