package com.example.proyecto_movil.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.local.ArtistRepository
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.ui.utils.*
import com.example.proyecto_movil.utils.ScreenBackground
import com.example.proyecto_movil.utils.SettingsIcon
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme

@Composable
fun ArtistPage(
    artistId: Int? = null,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onOpenAlbum: (Int) -> Unit = {},
    onSeeAll: () -> Unit = {}
) {
    val artist = artistId?.let { id ->
        ArtistRepository.artists.find { it.id == id }
    } ?: ArtistRepository.artists.firstOrNull()

    if (artist == null) return

    // Filtrar álbumes de este artista
    val albums = AlbumRepository.albums.filter { it.artist.id == artist.id }

    // 👇 elegir fondo dinámico según tema
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundRes = if (isDarkTheme) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = MaterialTheme.colorScheme.onSurface // 👈 adaptado
                    )
                }
                SettingsIcon()
            }

            Spacer(Modifier.height(20.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                FotoPerfilArtista(artist.profilePic)
                Spacer(Modifier.height(10.dp))
                TituloArtista(artist.name)
                Spacer(Modifier.height(16.dp))
                SeguidoresCantante("17K seguidores") // placeholder
                Spacer(Modifier.height(16.dp))
                SeguidoresCantante("Puntaje global")
                TextoReseñas("de 4500 reseñas")

                Spacer(Modifier.height(24.dp))
                TituloAlbum("Álbumes")
                Spacer(Modifier.height(12.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(albums) { alb ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable { onOpenAlbum(alb.id) }
                        ) {
                            FotoAlbumArtista(alb.coverRes)
                            TituloAlbumes(alb.title)
                            Spacer(Modifier.height(4.dp))
                            FechaAlbum(alb.year)
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = onSeeAll,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary, // 👈 theme
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Ver toda la discografía", fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(
    name = "ArtistPage Light",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ArtistPageLightPreview() {
    Proyecto_movilTheme(useDarkTheme = false) {
        ArtistPage()
    }
}

@Preview(
    name = "ArtistPage Dark",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ArtistPageDarkPreview() {
    Proyecto_movilTheme(useDarkTheme = true) {
        ArtistPage()
    }
}
