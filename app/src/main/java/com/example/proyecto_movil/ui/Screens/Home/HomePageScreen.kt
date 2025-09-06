package com.example.proyecto_movil.ui.Screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.ui.navigation.Screen
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.ScreenBackground

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAlbumClick: (AlbumUI) -> Unit = {},
    onHomeClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    homeViewModel: HomeViewModel = hiltViewModel(),
    stateOverride: HomeState? = null
) {
    val uiState: State<HomeState> = stateOverride?.let { remember { mutableStateOf(it) } }
        ?: homeViewModel.state.collectAsState()
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
            SearchBar(
                value = state.searchQuery,
                onValueChange = homeViewModel::updateSearchQuery
            )
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

@Composable
private fun HeaderSection() {
    Text(
        text = "Descubre música",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
private fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit
) {
    androidx.compose.material3.OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Buscar álbumes, artistas...") },
        singleLine = true
    )
}

@Composable
private fun SectionRow(
    title: String,
    albums: List<AlbumUI>,
    onAlbumClick: (AlbumUI) -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(albums) { album ->
                AlbumCard(album = album) { onAlbumClick(album) }
            }
        }
    }
}

@Composable
private fun AlbumCard(
    album: AlbumUI,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .width(140.dp)
            .heightIn(min = 180.dp)
    ) {
        Image(
            painter = painterResource(id = album.coverRes),
            contentDescription = album.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(8.dp))
        Column(Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            Text(
                text = album.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = album.artist.name,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Surface(
        tonalElevation = 3.dp,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomTab(
                icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
                label = "Inicio",
                active = true,
                onClick = onHomeClick
            )
            BottomTab(
                icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Perfil") },
                label = "Perfil",
                active = false,
                onClick = onProfileClick
            )
        }
    }
}

@Composable
private fun BottomTab(
    icon: @Composable () -> Unit,
    label: String,
    active: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon()
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = if (active) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun HomeScreenRoute(navController: NavController) {
    HomeScreen(
        onAlbumClick = { album -> navController.navigate("${Screen.Album.route}/${album.id}") },
        onHomeClick = {},
        onProfileClick = { navController.navigate(Screen.Profile.createRoute(6)) }
    )
}

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
        onHomeClick = {},
        onProfileClick = {},
        stateOverride = state
    )
}