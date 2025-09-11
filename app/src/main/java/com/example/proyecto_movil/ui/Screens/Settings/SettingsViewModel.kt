package com.example.proyecto_movil.ui.Screens.Settings

import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(
        SettingsState(
            username = "el.xokas",
            avatarRes = R.drawable.xocas,
            preferDarkMode = true,
            hideActivity = true,
            showRecentAlbums = true,
            publicPlaylists = false,
            showPlaylistsOnProfile = true,
            showFollowersAndFollowing = true,
            allowExplicitContent = true,
            showUnavailableInCountry = false,
            pushNotifications = true,
            emailNotifications = true
        )
    )
    val uiState: StateFlow<SettingsState> = _uiState

    fun onBackClicked() = _uiState.update { s -> s.copy(navigateBack = true) }
    fun consumeNavigation() = _uiState.update { s -> s.copy(navigateBack = false) }

    fun togglePreferDarkMode() = _uiState.update { s -> s.copy(preferDarkMode = !s.preferDarkMode) }

    fun toggleHideActivity() = _uiState.update { s -> s.copy(hideActivity = !s.hideActivity) }
    fun toggleShowRecentAlbums() = _uiState.update { s -> s.copy(showRecentAlbums = !s.showRecentAlbums) }
    fun togglePublicPlaylists() = _uiState.update { s -> s.copy(publicPlaylists = !s.publicPlaylists) }
    fun toggleShowPlaylistsOnProfile() = _uiState.update { s -> s.copy(showPlaylistsOnProfile = !s.showPlaylistsOnProfile) }
    fun toggleShowFollowersAndFollowing() = _uiState.update { s -> s.copy(showFollowersAndFollowing = !s.showFollowersAndFollowing) }

    fun toggleAllowExplicitContent() = _uiState.update { s -> s.copy(allowExplicitContent = !s.allowExplicitContent) }
    fun toggleShowUnavailableInCountry() = _uiState.update { s -> s.copy(showUnavailableInCountry = !s.showUnavailableInCountry) }

    fun togglePushNotifications() = _uiState.update { s -> s.copy(pushNotifications = !s.pushNotifications) }
    fun toggleEmailNotifications() = _uiState.update { s -> s.copy(emailNotifications = !s.emailNotifications) }

    fun onLanguageClicked() {}
    fun onViewProfileClicked() {}
    fun onLogoutClicked() {}
    fun onDeactivateAccountClicked() {}
}
