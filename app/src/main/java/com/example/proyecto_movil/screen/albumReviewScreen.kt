package com.example.proyecto_movil.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.utils.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun albumReviewScreen(
    modifier: Modifier = Modifier
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

            // Usa tu componente existente de reseña
            com.example.proyecto_movil.utils.UserReview(
                userImage = R.drawable.drake,
                userName = stringResource(id = R.string.persona1_user),
                reviewText = stringResource(id = R.string.persona1_reseña),
                isLiked = true
            )
            com.example.proyecto_movil.utils.UserReview(
                userImage = R.drawable.tyler,
                userName = stringResource(id = R.string.persona2_user),
                reviewText = stringResource(id = R.string.persona2_reseña),
                isLiked = true
            )
        }
    }
}

@Preview(showBackground = true, name = "AlbumReviewScreen Preview")
@Composable
fun albumReviewScreenPreview() {
    albumReviewScreen()
}
