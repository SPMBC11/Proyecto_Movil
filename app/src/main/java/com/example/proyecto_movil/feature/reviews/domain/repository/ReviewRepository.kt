package com.example.proyecto_movil.feature.reviews.domain.repository

import com.example.proyecto_movil.feature.reviews.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun getReviewsForAlbum(albumId: Int): Flow<List<Review>>
    suspend fun addReview(albumId: Int, review: Review)
}
