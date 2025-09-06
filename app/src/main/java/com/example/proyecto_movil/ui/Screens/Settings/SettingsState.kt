package com.example.proyecto_movil.ui.Screens.Settings

data class SettingsState(
    val username: String = "",
    val avatarRes: Int = 0,

    val preferDarkMode: Boolean = true,

    val hideActivity: Boolean = true,
    val showRecentAlbums: Boolean = true,
    val publicPlaylists: Boolean = false,
    val showPlaylistsOnProfile: Boolean = true,
    val showFollowersAndFollowing: Boolean = true,

    val allowExplicitContent: Boolean = true,
    val showUnavailableInCountry: Boolean = false,

    val pushNotifications: Boolean = true,
    val emailNotifications: Boolean = true,

    val navigateBack: Boolean = false
)
