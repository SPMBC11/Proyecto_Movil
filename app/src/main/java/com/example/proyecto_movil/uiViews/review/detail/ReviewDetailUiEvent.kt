package com.example.proyecto_movil.uiViews.review.detail

sealed class ReviewDetailUiEvent {
    data class OnLoad(val id: String) : ReviewDetailUiEvent()
    data object OnToggleLike : ReviewDetailUiEvent()
    data object OnRetry : ReviewDetailUiEvent()
    data object OnErrorConsumed : ReviewDetailUiEvent()
}