package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.utils.ScreenBackground

// Definiciones de datos de ejemplo (mock data)
data class AlbumItem(
    val id: Int,
    val coverRes: Int,
    val title: String,
    val year: String
)

val mockAlbums = listOf(
    AlbumItem(1, R.drawable.dosmil, "2000", "2000"),
    AlbumItem(2, R.drawable.presidente_champeta, "El presidente de la Champeta", "2015"),
    AlbumItem(3, R.drawable.goodforyou, "GOODFORYOU", "2023"),
    AlbumItem(4, R.drawable.party_rock, "Party Rock", "2011")
)

@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    ScreenBackground(backgroundRes = R.drawable.fondocriti, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Encabezado
            HeaderSection()

            // Información de la lista y del usuario
            ListInfoSection()

            // Cuadrícula de álbumes
            AlbumGrid(albums = mockAlbums)
        }
    }
}

@Composable
private fun HeaderSection() {
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
            tint = Color.White,
            modifier = Modifier
                .size(30.dp)
                .clickable { /* TODO: Navegación hacia atrás */ }
        )
        Text(
            text = "Lista",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Ajustes",
            tint = Color.White,
            modifier = Modifier
                .size(30.dp)
                .clickable { /* TODO: Lógica de ajustes */ }
        )
    }
}

@Composable
private fun ListInfoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 24.dp)
    ) {
        // Información del creador
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.xocas),
                contentDescription = "Avatar de el.xokas",
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "el.xokas",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(16.dp))

        // Título y descripción
        Text(
            text = "Canciónes que atentaron contra mi estabilidad emocional",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 32.sp
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Pero la queria tanto que daba igual porque cinco minutos con ella significaban diez horas con cualquier otra entiendes ese nivel de vibración no lo había tenido nunca con veinte años yo ha había estado con bastantes tias.",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 12.sp
        )

        Spacer(Modifier.height(16.dp))

        // Estadísticas (Likes y reproducciones)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Likes",
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "0 likes",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Headphones,
                    contentDescription = "Reproducciones",
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "100%",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun AlbumGrid(albums: List<AlbumItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(albums) { album ->
            AlbumCard(album = album)
        }
    }
}

@Composable
private fun AlbumCard(album: AlbumItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Lógica al hacer clic */ },
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
                    .aspectRatio(1f) // Para que la imagen sea cuadrada
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            // Icono de reproducción
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.7f))
                    .clickable { /* TODO: Lógica para reproducir */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.xocas),
                    contentDescription = "Reproducir",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = album.title,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = album.year,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 10.sp
        )
    }
}

@Preview(showBackground = true, name = "ListScreen Preview")
@Composable
fun ListScreenPreview() {
    Proyecto_movilTheme {
        ListScreen()
    }
}