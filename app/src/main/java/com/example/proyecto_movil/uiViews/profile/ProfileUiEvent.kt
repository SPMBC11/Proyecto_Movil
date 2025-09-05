package com.example.proyecto_movil.uiViews.profile

sealed class ProfileUiEvent {
    data object OnLoad : ProfileUiEvent()
    data object OnRefresh : ProfileUiEvent()
    data class OnUpdateBio(val value: String) : ProfileUiEvent()
    data object OnSaveProfile : ProfileUiEvent()
    data object OnErrorConsumed : ProfileUiEvent()
}