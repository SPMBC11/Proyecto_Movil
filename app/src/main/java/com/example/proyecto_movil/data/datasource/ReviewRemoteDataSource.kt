package com.example.proyecto_movil.data.datasource

import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.dtos.CreateReviewDto
import com.example.proyecto_movil.data.dtos.ReviewDto
import kotlinx.coroutines.flow.Flow

interface ReviewRemoteDataSource {
    suspend fun getAllReviews(): List<ReviewDto>
  suspend fun getReviewById(id: String,currentUserId: String= ""): ReviewDto
  suspend fun createReview(review: CreateReviewDto): Unit
  suspend fun deleteReview(id: String): Unit?
  suspend fun updateReview(id: String, review: CreateReviewDto): Unit
    suspend fun getReviewReplies(id: String): List<ReviewDto>

    suspend fun sendOrDeleteLike(reviewId: String, liked: Boolean): Unit
    fun ListenAllReviews(): Flow<List<ReviewInfo>>
}