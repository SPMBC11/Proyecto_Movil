package com.example.proyecto_movil.uiViews.album

sealed class AlbumReviewUiEvent {
    data class OnLoad(val albumId: Int) : AlbumReviewUiEvent()
}