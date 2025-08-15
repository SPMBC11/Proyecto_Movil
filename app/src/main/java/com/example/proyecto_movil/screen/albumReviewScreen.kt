package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.utils.UserReview // Importa el nuevo Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun albumReviewScreen(
    modifier: Modifier = Modifier
) {
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
            tint = Color.White
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 55.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(id = R.string.titulo_resenas),
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.mcmiller),
                contentDescription = stringResource(id = R.string.imagen_album),
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.titulo_album),
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.artista_album),
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.ano_album),
                color = Color.White,
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
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                    Text(
                        text = stringResource(id = R.string.cantidad_usuarios_alb),
                        color = Color.Gray,
                        fontSize = 12.sp,
                    )
                }

                OutlinedTextField(
                    value = "97%",
                    onValueChange = {},
                    readOnly = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .padding(end = 150.dp, start = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.resenas_album),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, end = 220.dp)
            )

            Text(
                text = stringResource(id = R.string.tipo_resenas),
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 125.dp, top = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            //Nuevo composable
            UserReview(
                userImage = R.drawable.drake,
                userName = stringResource(id = R.string.persona1_user),
                reviewText = stringResource(id = R.string.persona1_reseña),
                isLiked = true // Cora lleno
            )

            UserReview(
                userImage = R.drawable.tyler,
                userName = stringResource(id = R.string.persona2_user),
                reviewText = stringResource(id = R.string.persona2_reseña),
                isLiked = true // Cora lleno
            )
        }
    }
}

@Preview(showBackground = true, name = "AlbumReviewScreen Preview")
@Composable
fun albumReviewScreenPreview() {
    albumReviewScreen()
}