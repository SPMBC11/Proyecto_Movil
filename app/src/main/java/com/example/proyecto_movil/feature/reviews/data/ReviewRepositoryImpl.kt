package com.example.proyecto_movil.feature.reviews.data

import com.example.proyecto_movil.data.local.FalseReviewRepository
import com.example.proyecto_movil.feature.reviews.domain.model.Review
import com.example.proyecto_movil.feature.reviews.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReviewRepositoryImpl : ReviewRepository {
    private val store = mutableMapOf<Int, MutableStateFlow<List<Review>>>()

    init {
        val seed = FalseReviewRepository.Reviews.map {
            Review(userImageRes = it.imageId, userName = it.username, content = it.content, liked = true)
        }
        store[101] = MutableStateFlow(seed) // álbum demo
    }

    override fun getReviewsForAlbum(albumId: Int): Flow<List<Review>> =
        store.getOrPut(albumId) { MutableStateFlow(emptyList()) }.asStateFlow()

    override suspend fun addReview(albumId: Int, review: Review) {
        val flow = store.getOrPut(albumId) { MutableStateFlow(emptyList()) }
        flow.value = flow.value + review
    }
}
