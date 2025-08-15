package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(
    modifier: Modifier = Modifier,
    onPublicarClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {}
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
                    text = stringResource(id = R.string.titulo_agreresenas),
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.mcmiller),
                contentDescription = "Imagen centrada",
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.fecha_resena),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 25.dp)
                )
                OutlinedTextField(
                    value = "7 de Julio de 2025",
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
                        .weight(1f)
                        .padding(horizontal = 45.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.puntaje_resena),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 9.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre el OutlinedTextField y la imagen
                ) {
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
                        modifier = Modifier.width(120.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.coravacio),
                        contentDescription = "Corazón de puntuación",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Text(
                text = stringResource(id = R.string.agrega_resena),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 16.dp)
            )
            OutlinedTextField(
                value = "Aqui puedes agregar una reseña...",
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
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = onCancelarClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF333333)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.cancelar_resena), color = Color.White)
                }
                Button(
                    onClick = onPublicarClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF06A0B5)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.publicar_resena), color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "AddReviewScreen Preview")
@Composable
fun AddReviewScreenPreview() {
    AddReviewScreen()
}