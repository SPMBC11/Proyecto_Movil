package com.example.proyecto_movil.feature.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.data.local.AlbumProfInfo
import com.example.proyecto_movil.data.local.ProfileInfo
import com.example.proyecto_movil.data.local.ReviewProfInfo
import com.example.proyecto_movil.feature.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UserProfileUiState(
    val isLoading: Boolean = true,
    val profileInfo: ProfileInfo? = null,
    val favoriteAlbums: List<AlbumProfInfo> = emptyList(),
    val userReviews: List<ReviewProfInfo> = emptyList(),
    val error: String? = null
)

class UserProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UserProfileUiState())
    val state: StateFlow<UserProfileUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                profileRepository.getProfileInfo(),
                profileRepository.getFavoriteAlbums(),
                profileRepository.getUserReviews()
            ) { profile, albums, reviews -> Triple(profile, albums, reviews) }
                .collect { (profile, albums, reviews) ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            profileInfo = profile,
                            favoriteAlbums = albums,
                            userReviews = reviews,
                            error = null
                        )
                    }
                }
        }
    }
}

