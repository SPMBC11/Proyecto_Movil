package com.example.proyecto_movil.ui.Screens.Lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.ScreenBackground

@Composable
fun ListScreen(
    viewModel: ListViewModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onSettings: () -> Unit = {},
    onOpenAlbum: (Int) -> Unit = {},
    onPlayAlbum: (Int) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.navigateBack, state.navigateToSettings, state.openAlbumId, state.playAlbumId) {
        if (state.navigateBack) { onBack(); viewModel.consumeBack() }
        if (state.navigateToSettings) { onSettings(); viewModel.consumeSettings() }
        state.openAlbumId?.let { onOpenAlbum(it); viewModel.consumeOpenAlbum() }
        state.playAlbumId?.let { onPlayAlbum(it); viewModel.consumePlayAlbum() }
    }

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            HeaderSection(
                title = state.title,
                onBack = { viewModel.onBackClicked() },
                onSettings = { viewModel.onSettingsClicked() }
            )
            ListInfoSection(
                creatorName = state.creatorName,
                creatorAvatarRes = state.creatorAvatarRes,
                title = "Canciones que atentaron contra mi estabilidad emocional",
                description = state.description,
                likes = state.likes,
                listenPercent = state.listenPercent
            )
            AlbumGrid(
                albums = state.albums,
                onOpen = { id -> viewModel.onAlbumClicked(id) },
                onPlay = { id -> viewModel.onPlayClicked(id) }
            )
        }
    }
}

@Composable
private fun HeaderSection(
    title: String,
    onBack: () -> Unit,
    onSettings: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Volver",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(30.dp)
                .clickable { onBack() }
        )
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Ajustes",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(30.dp)
                .clickable { onSettings() }
        )
    }
}

@Composable
private fun ListInfoSection(
    creatorName: String,
    creatorAvatarRes: Int,
    title: String,
    description: String,
    likes: Int,
    listenPercent: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = creatorAvatarRes),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = creatorName,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 28.sp
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = description,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Likes",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "$likes likes",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Headphones,
                    contentDescription = "Reproducciones",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = listenPercent,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun AlbumGrid(
    albums: List<AlbumItem>,
    onOpen: (Int) -> Unit,
    onPlay: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(albums) { album ->
            AlbumCard(album = album, onOpen = { onOpen(album.id) }, onPlay = { onPlay(album.id) })
        }
    }
}

@Composable
private fun AlbumCard(
    album: AlbumItem,
    onOpen: () -> Unit,
    onPlay: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = album.coverRes),
                contentDescription = album.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    .clickable { onPlay() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Reproducir",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = album.title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = album.year,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp
        )
    }
}

@Preview(name = "ListScreen Light", showSystemUi = true)
@Composable
fun ListScreenPreviewLight() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { ListViewModel() }
            ListScreen(viewModel = vm)
        }
    }
}

@Preview(name = "ListScreen Dark", showSystemUi = true)
@Composable
fun ListScreenPreviewDark() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { ListViewModel() }
            ListScreen(viewModel = vm)
        }
    }
}
