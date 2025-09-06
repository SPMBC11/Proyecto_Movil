package com.example.proyecto_movil.ui.Screens.AlbumReviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.ui.components.ReviewCard
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.*

@Composable
fun AlbumReviewScreen(
    album: AlbumUI,
    viewModel: AlbumReviewViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onArtistClick: () -> Unit = {},
    onUserClick: (Int) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(album.id) { viewModel.setAlbum(album) }
    LaunchedEffect(state.navigateToArtist, state.openUserId) {
        if (state.navigateToArtist) {
            onArtistClick()
            viewModel.consumeNavigateArtist()
        }
        state.openUserId?.let { id ->
            onUserClick(id)
            viewModel.consumeOpenUser()
        }
    }

    val backgroundRes = if (isSystemInDarkTheme()) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        SettingsIcon(modifier = Modifier.align(Alignment.TopEnd))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 55.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                TitleBar(text = stringResource(id = R.string.titulo_resenas))
                Spacer(Modifier.height(16.dp))
            }

            item {
                AlbumHeader(
                    coverRes = state.albumCoverRes,
                    title = state.albumTitle,
                    artist = state.albumArtist,
                    year = state.albumYear
                )
            }

            item {
                Image(
                    painter = painterResource(id = state.artistProfileRes),
                    contentDescription = "Foto de ${state.albumArtist}",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .clickable { viewModel.onArtistClicked() },
                    contentScale = ContentScale.Crop
                )
            }

            item {
                ScoreRow(
                    scoreLabel = stringResource(id = R.string.puntaje_album),
                    usersHint = stringResource(id = R.string.cantidad_usuarios_alb),
                    scoreValue = state.avgPercent?.let { "$it%" } ?: "N/A"
                )
            }

            item {
                ClickableSectionTitle(
                    title = stringResource(id = R.string.resenas_album),
                    onSeeAll = { }
                )
            }

            if (state.reviews.isEmpty()) {
                item {
                    Text(
                        text = "Aún no hay reseñas para este álbum",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            } else {
                items(state.reviews) { review ->
                    ReviewCard(
                        review = review,
                        onUserClick = { userId -> viewModel.onUserClicked(userId) }
                    )
                }
            }
        }
    }
}

@Preview(name = "AlbumReview Light", showSystemUi = true)
@Composable
fun AlbumReviewScreenPreviewLight() {
    val album = AlbumRepository.albums.first()
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { AlbumReviewViewModel() }
            AlbumReviewScreen(album = album, viewModel = vm)
        }
    }
}

@Preview(name = "AlbumReview Dark", showSystemUi = true)
@Composable
fun AlbumReviewScreenPreviewDark() {
    val album = AlbumRepository.albums.first()
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { AlbumReviewViewModel() }
            AlbumReviewScreen(album = album, viewModel = vm)
        }
    }
}


