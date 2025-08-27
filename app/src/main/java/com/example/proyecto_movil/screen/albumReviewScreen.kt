package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable // 👈 nuevo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape // 👈 nuevo
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip // 👈 nuevo
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.local.FalseReviewRepository
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.utils.UserReview
import com.example.proyecto_movil.utils.AlbumHeader
import com.example.proyecto_movil.utils.TitleBar
import com.example.proyecto_movil.utils.ScoreRow
import com.example.proyecto_movil.utils.SectionTitle
import com.example.proyecto_movil.utils.ScreenBackground
import com.example.proyecto_movil.utils.SettingsIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun albumReviewScreen(
    modifier: Modifier = Modifier,
    onArtistClick: () -> Unit = {} // 👈 nuevo
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
                coverRes = R.drawable.mcmiller,
                title = stringResource(id = R.string.titulo_album),
                artist = stringResource(id = R.string.artista_album),
                year = stringResource(id = R.string.ano_album)
            )

            // 👇 Foto de perfil del artista, CLICKEABLE para ir a Artistpage
            Spacer(Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.sabrina), // usa el drawable de tu artista
                contentDescription = "Foto de perfil del artista",
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .clickable { onArtistClick() }, // 👈 navegación
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(20.dp))

            ScoreRow(
                scoreLabel = stringResource(id = R.string.puntaje_album),
                usersHint = stringResource(id = R.string.cantidad_usuarios_alb),
                scoreValue = "97%"
            )

            Spacer(Modifier.height(16.dp))

            SectionTitle(
                title = stringResource(id = R.string.resenas_album),
                subtitle = stringResource(id = R.string.tipo_resenas)
            )

            Spacer(Modifier.height(20.dp))

            Column {
                FalseReviewRepository.Reviews.forEach { review ->
                    UserReview(
                        userImage = review.imageId,
                        userName = review.username,
                        reviewText = review.content,
                        isLiked = true
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "AlbumReviewScreen Preview")
@Composable
fun albumReviewScreenPreview() {
    Proyecto_movilTheme {
        albumReviewScreen()
    }
}
