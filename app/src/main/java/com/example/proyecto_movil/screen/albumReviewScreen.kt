package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ReviewRepository
import com.example.proyecto_movil.navigation.AppNavHost
import com.example.proyecto_movil.navigation.Screen
import com.example.proyecto_movil.ui.components.ReviewCard
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.utils.*

@Composable
fun AlbumReviewScreen(
    album: AlbumUI,
    modifier: Modifier = Modifier,
    onArtistClick: () -> Unit = {},
    onUserClick: (Int) -> Unit = {}
) {
    val albumReviews = ReviewRepository.reviews.filter { it.album.id == album.id }

    val backgroundRes = if (isSystemInDarkTheme()) {
        R.drawable.fondocriti
    } else {
        R.drawable.fondocriti_light
    }

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        SettingsIcon(modifier = Modifier.align(Alignment.TopEnd))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 55.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp) // deja espacio al final
        ) {
            item {
                TitleBar(text = stringResource(id = R.string.titulo_resenas))
                Spacer(Modifier.height(16.dp))
            }

            item {
                AlbumHeader(
                    coverRes = album.coverRes,
                    title = album.title,
                    artist = album.artist.name,
                    year = album.year
                )
            }

            item {
                Image(
                    painter = painterResource(id = album.artist.profilePic),
                    contentDescription = "Foto de ${album.artist.name}",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .clickable { onArtistClick() },
                    contentScale = ContentScale.Crop
                )
            }

            item {
                ScoreRow(
                    scoreLabel = stringResource(id = R.string.puntaje_album),
                    usersHint = stringResource(id = R.string.cantidad_usuarios_alb),
                    scoreValue = if (albumReviews.isNotEmpty())
                        (albumReviews.map { it.score }.average() * 10).toInt().toString() + "%"
                    else "N/A"
                )
            }

            item {
                ClickableSectionTitle(
                    title = stringResource(id = R.string.resenas_album),
                    onSeeAll = { /* TODO */ }
                )
            }

            if (albumReviews.isEmpty()) {
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
                items(albumReviews) { review ->
                    ReviewCard(
                        review = review,
                        onUserClick = { userId -> onUserClick(userId) }
                    )
                }
            }
        }
    }
}


@Preview(name = "Light Mode", showSystemUi = true)
@Composable
fun AlbumReviewScreenPreviewLight() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AlbumReviewScreen(
                album = AlbumRepository.albums.first()
            )
        }
    }
}

@Preview(name = "Dark Mode", showSystemUi = true)
@Composable
fun AlbumReviewScreenPreviewDark() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AlbumReviewScreen(
                album = AlbumRepository.albums.first()
            )
        }
    }
}
