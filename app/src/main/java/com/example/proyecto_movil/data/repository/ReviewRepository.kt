package com.example.proyecto_movil.data.repository

import coil.network.HttpException
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.datasource.impl.ReviewRetrofitDataSourceImpl
import com.example.proyecto_movil.data.dtos.CreateReviewDto
import com.example.proyecto_movil.data.dtos.toReviewInfo
import jakarta.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewRemoteDataSource: ReviewRetrofitDataSourceImpl)
{
    suspend fun getReviews(): Result<List<ReviewInfo>> {
        return try{
            val reviews = reviewRemoteDataSource.getAllReviews()
            val reviewsInfo = reviews.map { it.toReviewInfo() }
            Result.success(reviewsInfo)
        }catch(e: HttpException){
            Result.failure(e)
        }
          catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun createReview(content: String, userId: String, parentReviewId: String?):Result<Unit>{
return try{
    val createReviewDto = CreateReviewDto(content, userId.toInt(), parentReviewId?.toInt())
reviewRemoteDataSource.createReview(createReviewDto)
    Result.success(Unit)
} catch(e: Exception){
    Result.failure(e)
}
    }
    suspend fun getReviewbyId(id: String): Result<ReviewInfo>{
        return try {
            val review = reviewRemoteDataSource.getReviewById(id)
            val reviewInfo = review.toReviewInfo()
            Result.success(reviewInfo)
        }catch(e: HttpException){
            Result.failure(e)
        }
    }
}