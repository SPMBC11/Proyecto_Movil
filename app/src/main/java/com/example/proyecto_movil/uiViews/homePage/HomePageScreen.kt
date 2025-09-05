package com.example.proyecto_movil.uiViews.homePage

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
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
    onHomeClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    homeViewModel: HomeViewModel = hiltViewModel(),
    stateOverride: HomeState? = null
) {
    val uiState: State<HomeState> = if (stateOverride != null) {
        remember { mutableStateOf(stateOverride) }
    } else {
        homeViewModel.state.collectAsState()
    }
    val state = uiState.value

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 52.dp, start = 16.dp, end = 16.dp, bottom = 72.dp)
        ) {
            HeaderSection()

            Spacer(Modifier.height(12.dp))
            SearchBar()
            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
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
                        albums = state.albumList,
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

        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onHomeClick = onHomeClick,
            onProfileClick = onProfileClick
        )
    }
}

/* ---------- Subcomponentes ---------- */

@Composable
private fun HeaderSection() { /* igual que ya lo tienes */ }

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
) { /* igual que ya lo tienes */ }

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit
) { /* igual que ya lo tienes */ }

@Composable
private fun BottomBarItem(
    icon: @Composable () -> Unit,
    label: String,
    active: Boolean,
    onClick: () -> Unit
) { /* igual que ya lo tienes */ }

/* ---------- Wrapper con navegación ---------- */
@Composable
fun HomeScreenRoute(navController: NavController) {
    HomeScreen(
        onAlbumClick = { album -> navController.navigate("${Screen.Album.route}/${album.id}") },
        onHomeClick = { /* ya estás en Home */ },
        onProfileClick = { navController.navigate(Screen.Profile.createRoute(6)) }
    )
}

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
        onHomeClick = { /* ya estás en Home */ },
        onProfileClick = { /* no-op */ },
        stateOverride = state
    )
}
