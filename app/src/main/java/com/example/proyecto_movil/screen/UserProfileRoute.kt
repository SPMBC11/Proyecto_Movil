package com.example.proyecto_movil.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.proyecto_movil.feature.profile.ui.UserProfileViewModel

@Composable
fun UserProfileRoute(
    vm: UserProfileViewModel,
    onEditProfile: () -> Unit,
    onSettings: () -> Unit,
    onOpenContent: () -> Unit,
    onBack: () -> Unit
) {
    val uiState = vm.state.collectAsStateWithLifecycle().value

    if (uiState.profileInfo == null) {
        // Simple loading for now
        androidx.compose.material3.CircularProgressIndicator()
        return
    }

    UserProfileScreen(
        isCurrentUserProfile = true,
        onEditProfile = onEditProfile,
        onSettings = onSettings,
        onOpenContent = onOpenContent,
        onBack = onBack
    )
}
