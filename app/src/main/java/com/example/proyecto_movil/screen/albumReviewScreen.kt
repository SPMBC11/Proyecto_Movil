package com.example.proyecto_movil.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
<<<<<<< HEAD
=======
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
>>>>>>> origin/master
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< HEAD
=======
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
>>>>>>> origin/master
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
<<<<<<< HEAD
import com.example.proyecto_movil.utils.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
=======
import com.example.proyecto_movil.data.local.FalseReviewRepository
import com.example.proyecto_movil.utils.UserReview
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
>>>>>>> origin/master

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun albumReviewScreen(
    modifier: Modifier = Modifier
) {
<<<<<<< HEAD
    ScreenBackground(backgroundRes = R.drawable.fondocriti, modifier = modifier) {
        SettingsIcon(modifier = Modifier.align(Alignment.TopEnd))
=======
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondocriti),
            contentDescription = stringResource(id = R.string.fondo_degradado),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = stringResource(id = R.string.ajustes_icon),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(30.dp)
                .size(30.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
>>>>>>> origin/master

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 55.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
<<<<<<< HEAD
            TitleBar(text = stringResource(id = R.string.titulo_resenas))
=======
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(id = R.string.titulo_resenas),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
>>>>>>> origin/master

            Spacer(Modifier.height(32.dp))

            AlbumHeader(
                coverRes = R.drawable.mcmiller,
                title = stringResource(id = R.string.titulo_album),
                artist = stringResource(id = R.string.artista_album),
                year = stringResource(id = R.string.ano_album)
            )

            Spacer(Modifier.height(20.dp))

<<<<<<< HEAD
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
=======
            Text(
                text = stringResource(id = R.string.titulo_album),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.artista_album),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.ano_album),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = stringResource(id = R.string.puntaje_album),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                    Text(
                        text = stringResource(id = R.string.cantidad_usuarios_alb),
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 12.sp,
                    )
                }

                OutlinedTextField(
                    value = "97%",
                    onValueChange = {},
                    readOnly = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        cursorColor = MaterialTheme.colorScheme.onPrimary,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .padding(end = 150.dp, start = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.resenas_album),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, end = 220.dp)
            )

            Text(
                text = stringResource(id = R.string.tipo_resenas),
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 125.dp, top = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Column {
                FalseReviewRepository.Reviews.forEach { review ->
                    UserReview(
                        userImage = review.imageId,
                        userName = stringResource(id = review.usernameId),
                        reviewText = stringResource(id = review.contentId),
                        isLiked = true
                    )
                }
            }
>>>>>>> origin/master
        }
    }
}

@Preview(showBackground = true, name = "AlbumReviewScreen Preview")
@Composable
fun albumReviewScreenPreview() {
<<<<<<< HEAD
    albumReviewScreen()
}
=======
    Proyecto_MovilTheme {
        albumReviewScreen()
    }
}
>>>>>>> origin/master
