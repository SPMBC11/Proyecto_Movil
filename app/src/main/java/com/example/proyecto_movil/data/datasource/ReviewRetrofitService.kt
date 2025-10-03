package com.example.proyecto_movil.data.datasource

import com.example.proyecto_movil.data.dtos.CreateReviewDto
import com.example.proyecto_movil.data.dtos.ReviewDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReviewRetrofitService {

    @GET("reviews")
    suspend fun getAllReviews(): List<ReviewDto>

    @POST("reviews")
    suspend fun createReview(@Body review: CreateReviewDto): Unit

    @DELETE("reviews/{id}")
    suspend fun deleteReview(@Path("id") id: String): Unit

    @PUT("reviews/{id}")
    suspend fun updateReview(@Path("id") id: String, @Body review: CreateReviewDto): Unit

    @GET("reviews/{id}")
    suspend fun getReviewById(@Path("id") id: String): ReviewDto

    @GET("reviews/{id}/replies")
    suspend fun getReviewReplies(@Path("id") id: String): List<ReviewDto>


}