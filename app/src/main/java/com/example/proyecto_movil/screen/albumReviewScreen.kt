package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.utils.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumReviewScreen(
    album: AlbumUI,
    modifier: Modifier = Modifier,
    onArtistClick: () -> Unit = {}
) {
    val albumReviews = ReviewRepository.reviews.filter { it.album.id == album.id }

    ScreenBackground(backgroundRes = R.drawable.fondocriti, modifier = modifier) {
        SettingsIcon(modifier = Modifier.align(Alignment.TopEnd))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 55.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleBar(text = stringResource(id = R.string.titulo_resenas))

            Spacer(Modifier.height(32.dp))

            AlbumHeader(
                coverRes = album.coverRes,
                title    = album.title,
                artist   = album.artist.name,
                year     = album.year
            )

            Spacer(Modifier.height(16.dp))

            Image(
                painter = painterResource(id = album.artist.profilePic),
                contentDescription = "Foto de ${album.artist.name}",
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .clickable { onArtistClick() },
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(20.dp))

            ScoreRow(
                scoreLabel = stringResource(id = R.string.puntaje_album),
                usersHint  = stringResource(id = R.string.cantidad_usuarios_alb),
                scoreValue = if (albumReviews.isNotEmpty())
                    (albumReviews.map { it.score }.average() * 10).toInt().toString() + "%"
                else
                    "N/A"
            )

            Spacer(Modifier.height(16.dp))

            ClickableSectionTitle(
                title = stringResource(id = R.string.resenas_album),
                onSeeAll = { /* Navegar a todas las reseñas */ }
            )

            Spacer(Modifier.height(20.dp))

            Column {
                if (albumReviews.isEmpty()) {
                    androidx.compose.material3.Text(
                        text = "Aún no hay reseñas para este álbum",
                        color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.padding(8.dp)
                    )
                } else {
                    albumReviews.forEach { review ->
                        UserReview(
                            userImage = review.user.profilePic,
                            userName  = review.user.username,
                            reviewText= review.content,
                            isLiked   = review.score > 6
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumReviewScreenPreview() {
    Proyecto_movilTheme {
        AlbumReviewScreen(
            album = AlbumRepository.albums.first()
        )
    }
}
