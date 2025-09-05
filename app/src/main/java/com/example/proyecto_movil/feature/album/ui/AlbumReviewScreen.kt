package com.example.proyecto_movil.feature.album.ui

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
import com.example.proyecto_movil.data.local.FalseReviewRepository
import com.example.proyecto_movil.ui_components.UserReview
import com.example.proyecto_movil.ui_components.AlbumHeader
import com.example.proyecto_movil.ui_components.TitleBar
import com.example.proyecto_movil.ui_components.ScoreRow
import com.example.proyecto_movil.ui_components.SectionTitle
import com.example.proyecto_movil.ui_components.ScreenBackground
import com.example.proyecto_movil.ui_components.SettingsIcon
import com.example.proyecto_movil.utils.recursos.AlbumUi
import com.example.proyecto_movil.utils.recursos.ArtistUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun albumReviewScreen(
    album: AlbumUi,
    modifier: Modifier = Modifier,
    onArtistClick: () -> Unit = {}
) {
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
                coverRes = album.coverRes,     // ✅
                title    = album.title,         // ✅
                artist   = album.artist.name,   // ✅
                year     = album.year           // ✅
            )

            Spacer(Modifier.height(16.dp))

            Image(
                painter = painterResource(id = album.coverRes), // ✅ nada de R.drawable.sabrina
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
                scoreValue = "97%"
            )

            Spacer(Modifier.height(16.dp))

            SectionTitle(
                title    = stringResource(id = R.string.resenas_album),
                subtitle = stringResource(id = R.string.tipo_resenas)
            )

            Spacer(Modifier.height(20.dp))

            Column {
                FalseReviewRepository.Reviews.forEach { review ->
                    UserReview(
                        userImage = review.imageId,
                        userName  = review.username,
                        reviewText= review.content,
                        isLiked   = true
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "AlbumReviewScreen Preview")
@Composable
fun albumReviewScreenPreview() {
    val sampleAlbum = AlbumUi(
        id = 1,
        coverRes = R.drawable.tyler_dttg,
        title = "DON'T TAP THE GLASS",
        year = "2024",
        artist = ArtistUI(
            id = 1,
            name = "Tyler, The Creator",
            genre = "Hip Hop",
            displayName = "Tyler, The Creator"
        )
    )

    albumReviewScreen(
        album = sampleAlbum,
        modifier = Modifier,
        onArtistClick = {}
    )
}
