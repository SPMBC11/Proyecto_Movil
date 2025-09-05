package com.example.proyecto_movil.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavBackStackEntry
import com.example.proyecto_movil.core.ServiceLocator
import com.example.proyecto_movil.feature.album.ui.AlbumReviewViewModel
import com.example.proyecto_movil.feature.album.ui.albumReviewScreen

@Composable
fun AlbumReviewRoute(
    backStackEntry: NavBackStackEntry,
    onArtistClick: () -> Unit
) {
    val vm: AlbumReviewViewModel = viewModel(
        viewModelStoreOwner = backStackEntry,
        factory = viewModelFactory {
            initializer {
                AlbumReviewViewModel(
                    albumRepository = ServiceLocator.albumRepository,
                    reviewRepository = ServiceLocator.reviewRepository,
                    savedStateHandle = createSavedStateHandle()
                )
            }
        }
    )

    val uiState = vm.state.collectAsStateWithLifecycle().value

    when {
        uiState.isLoading -> {
            LoadingScreen()
        }
        uiState.error != null -> {
            SimpleErrorScreen(uiState.error ?: "Error")
        }
        uiState.album != null -> {
            albumReviewScreen(
                album = uiState.album,
                onArtistClick = onArtistClick
            )
        }
    }
}

@Composable
private fun LoadingScreen() {
    androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        androidx.compose.material3.CircularProgressIndicator()
    }
}

@Composable
private fun SimpleErrorScreen(message: String) {
    androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        androidx.compose.material3.Text(text = message)
    }
}

