package com.example.proyecto_movil.feature.reviews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.core.ServiceLocator
import com.example.proyecto_movil.feature.reviews.domain.model.Review
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AlbumReviewsViewModel(private val albumId: Int) : ViewModel() {
    private val repo = ServiceLocator.reviewRepository
    val reviews: StateFlow<List<Review>> =
        repo.getReviewsForAlbum(albumId).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(review: Review) = viewModelScope.launch {
        repo.addReview(albumId, review)
    }
}
