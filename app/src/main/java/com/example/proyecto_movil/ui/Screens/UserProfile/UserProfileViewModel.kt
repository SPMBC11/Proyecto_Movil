package com.example.proyecto_movil.ui.Screens.UserProfile

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.UserUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UserProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserProfileState())
    val uiState: StateFlow<UserProfileState> = _uiState

    fun setInitialData(user: UserUI, reviews: List<ReviewInfo>) {
        val fav = user.playlists.firstOrNull()?.albums ?: emptyList()
        _uiState.update {
            it.copy(
                username = user.username,
                avatarRes = user.profilePic,
                followers = user.followers,
                following = user.following,
                favoriteAlbums = fav,
                reviews = reviews
            )
        }
    }

    fun onBackClicked() = _uiState.update { s -> s.copy(navigateBack = true) }
    fun onSettingsClicked() = _uiState.update { s -> s.copy(navigateToSettings = true) }
    fun onEditProfileClicked() = _uiState.update { s -> s.copy(navigateToEditProfile = true) }
    fun onAlbumClicked(album: AlbumUI) = _uiState.update { s -> s.copy(openAlbumId = album.id) }
    fun onReviewClicked(review: ReviewInfo) = _uiState.update { s -> s.copy(openReview = review) }

    fun consumeBack() = _uiState.update { s -> s.copy(navigateBack = false) }
    fun consumeSettings() = _uiState.update { s -> s.copy(navigateToSettings = false) }
    fun consumeEdit() = _uiState.update { s -> s.copy(navigateToEditProfile = false) }
    fun consumeOpenAlbum() = _uiState.update { s -> s.copy(openAlbumId = null) }
    fun consumeOpenReview() = _uiState.update { s -> s.copy(openReview = null) }
}
