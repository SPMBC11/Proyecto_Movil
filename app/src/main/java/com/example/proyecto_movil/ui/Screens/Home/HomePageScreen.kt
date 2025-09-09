package com.example.proyecto_movil.uiViews.homePage

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.ui.Screens.Home.HomeState
import com.example.proyecto_movil.ui.Screens.Home.HomeViewModel
import com.example.proyecto_movil.navigation.Screen
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.ScreenBackground


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    onAlbumClick: (AlbumUI) -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onSearchChanged: (String) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    // Dispatch de side-effects como en ListScreen
    LaunchedEffect(
        state.navigateToProfile,
        state.navigateToSettings,
        state.openAlbum
    ) {
        if (state.navigateToProfile) { onProfileClick(); viewModel.consumeProfile() }
        if (state.navigateToSettings) { onSettingsClick(); viewModel.consumeSettings() }
        state.openAlbum?.let { onAlbumClick(it); viewModel.consumeOpenAlbum() }
    }

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            // Usa tu subcomponente
            SearchBar(
                value = state.searchQuery,
                onValueChange = {
                    viewModel.updateSearchQuery(it)
                    onSearchChanged(it)
                },
                onProfileClick = { viewModel.onProfileClicked() },
                onSettingsClick = { viewModel.onSettingsClicked() }
            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    SectionRow(
                        title = "Novedades",
                        albums = viewModel.getNewReleases(),
                        onAlbumClick = { album -> viewModel.onAlbumClicked(album) }
                    )
                }
                item {
                    SectionRow(
                        title = "Nuevo entre amigos",
                        albums = state.albumList,
                        onAlbumClick = { album -> viewModel.onAlbumClicked(album) }
                    )
                }
                item {
                    SectionRow(
                        title = "Popular entre amigos",
                        albums = viewModel.getPopularAlbums(),
                        onAlbumClick = { album -> viewModel.onAlbumClicked(album) }
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
            HomeScreenRoutePreview(navController)
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
            HomeScreenRoutePreview(navController)
        }
    }
}

@Composable
private fun HomeScreenRoutePreview(navController: NavController) {
    val vm = remember { HomeViewModel() }
    HomeScreen(
        viewModel = vm,
        onAlbumClick = { album -> navController.navigate("${Screen.Album.route}/${album.id}") }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Buscar álbum...") },
            singleLine = true
        )
        Spacer(Modifier.width(8.dp))
        IconButton(onClick = onProfileClick) {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Perfil")
        }
        IconButton(onClick = onSettingsClick) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "Ajustes")
        }
    }
}
