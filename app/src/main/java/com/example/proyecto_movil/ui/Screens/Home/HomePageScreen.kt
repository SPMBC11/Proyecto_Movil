package com.example.proyecto_movil.uiViews.homePage

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.navigation.Screen
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.utils.ScreenBackground

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAlbumClick: (AlbumUI) -> Unit = {},
    homeViewModel: HomeViewModel = hiltViewModel(),
    stateOverride: HomeState? = null
) {
    val state by homeViewModel.state.collectAsState()
    val currentState = stateOverride ?: state

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp) // padding más estándar
        ) {
            SearchBar()
            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    SectionRow(
                        title = "Novedades",
                        albums = AlbumRepository.albums.filter { it.id in listOf(601, 201, 501) },
                        onAlbumClick = onAlbumClick
                    )
                }
                item {
                    SectionRow(
                        title = "Nuevo entre amigos",
                        albums = currentState.albumList,
                        onAlbumClick = onAlbumClick
                    )
                }
                item {
                    SectionRow(
                        title = "Popular entre amigos",
                        albums = AlbumRepository.albums.filter { it.id in listOf(801, 701, 401) },
                        onAlbumClick = onAlbumClick
                    )
                }
            }
        }
    }
}

/* ---------- Subcomponentes ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar() { /* igual que ya lo tienes */ }

@Composable
private fun SectionRow(
    title: String,
    albums: List<AlbumUI>,
    onAlbumClick: (AlbumUI) -> Unit
) { /* igual que ya lo tienes */ }

@Composable
private fun AlbumCard(
    album: AlbumUI,
    onClick: () -> Unit
) { }

/* ---------- Previews ---------- */
@Preview(name = "HomeScreen Light", showSystemUi = true)
@Composable
fun HomeScreenPreviewLight() {
    val navController = rememberNavController()
    val previewState = HomeState(albumList = AlbumRepository.albums.take(6))
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreenRoutePreview(navController, previewState)
        }
    }
}

@Preview(name = "HomeScreen Dark", showSystemUi = true)
@Composable
fun HomeScreenPreviewDark() {
    val navController = rememberNavController()
    val previewState = HomeState(albumList = AlbumRepository.albums.take(6))
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreenRoutePreview(navController, previewState)
        }
    }
}

@Composable
private fun HomeScreenRoutePreview(navController: NavController, state: HomeState) {
    HomeScreen(
        onAlbumClick = { album -> navController.navigate("${Screen.Album.route}/${album.id}") },
        stateOverride = state
    )
}
