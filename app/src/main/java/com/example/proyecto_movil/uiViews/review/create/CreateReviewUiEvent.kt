package com.example.proyecto_movil.uiViews.review.create

sealed class CreateReviewUiEvent {
    data class OnTitleChange(val value: String) : CreateReviewUiEvent()
    data class OnArtistChange(val value: String) : CreateReviewUiEvent()
    data class OnRatingChange(val value: Int) : CreateReviewUiEvent()
    data class OnContentChange(val value: String) : CreateReviewUiEvent()
    data object OnSubmit : CreateReviewUiEvent()
    data object OnErrorConsumed : CreateReviewUiEvent()
}