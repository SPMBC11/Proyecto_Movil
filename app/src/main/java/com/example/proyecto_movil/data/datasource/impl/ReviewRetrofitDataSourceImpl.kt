package com.example.proyecto_movil.data.datasource.impl

import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.datasource.ReviewRemoteDataSource
import com.example.proyecto_movil.data.datasource.ReviewRetrofitService
import com.example.proyecto_movil.data.dtos.CreateReviewDto
import com.example.proyecto_movil.data.dtos.ReviewDto
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ReviewRetrofitDataSourceImpl  @Inject constructor(
    val service: ReviewRetrofitService
): ReviewRemoteDataSource {
    override suspend fun getAllReviews(): List<ReviewDto> {
        return service.getAllReviews()
    }

    override suspend fun getReviewById(id: String, currentUserId: String
    ): ReviewDto {
        return service.getReviewById(id)
    }

    override suspend fun createReview(review: CreateReviewDto) {
        service.createReview(review)
    }

    override suspend fun deleteReview(id: String): Unit? {
        return service.deleteReview(id)
    }

    override suspend fun updateReview(id: String, review: CreateReviewDto
    ) {
        service.updateReview(id, review)
    }

    override suspend fun getReviewReplies(id: String): List<ReviewDto> {
        return service.getReviewReplies(id)
    }

    override suspend fun sendOrDeleteLike(reviewId: String, liked: Boolean) {
        TODO("Not yet implemented")
    }

    override fun ListenAllReviews(): Flow<List<ReviewInfo>> {
        TODO("Not yet implemented")
    }
}