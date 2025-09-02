package com.example.proyecto_movil.screen

import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ArtistRepository
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.*
import com.example.proyecto_movil.utils.ScreenBackground
import com.example.proyecto_movil.utils.SettingsIcon

@Composable
fun Content(
    artistId: Int? = null,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onOpenAlbum: (Int) -> Unit = {},
    onSeeAll: () -> Unit = {}
) {
    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    // Buscar artista si llega un ID
    val selectedArtist = artistId?.let { id ->
        ArtistRepository.artists.find { it.id == id }
    }

    // Filtrar álbumes según artista (o todos si no hay artistId)
    val albums = if (selectedArtist != null) {
        AlbumRepository.albums.filter { it.artist.id == selectedArtist.id }
    } else {
        AlbumRepository.albums
    }

    val headerTitle = selectedArtist?.name ?: "Todos"

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 16.dp, end = 16.dp)
        ) {
            // 🔹 TopBar con back + título + settings
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                TituloArtista(headerTitle)
                SettingsIcon(
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                // -------------------- Reseñas --------------------
                TituloAlbum("Reseñas")
                Spacer(Modifier.height(12.dp))

                albums.chunked(2).take(3).forEach { group ->
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        items(group) { alb ->
                            Row(
                                modifier = Modifier.clickable { onOpenAlbum(alb.id) }
                            ) {
                                FotoAlbumArtistaPeque(alb.coverRes)
                                Column(modifier = Modifier.padding(start = 8.dp)) {
                                    TituloAlbumPeque(alb.title)
                                    Spacer(Modifier.height(7.dp))
                                    TituloArtistaPeque(alb.artist.name)
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                }

                // -------------------- Listas --------------------
                TituloAlbum("Listas")
                Spacer(Modifier.height(15.dp))

                DescripcionLista("Canciones que atentaron contra mi estabilidad emocional")
                AlbumListRow(albums, onOpenAlbum)

                Spacer(Modifier.height(24.dp))

                DescripcionLista("10/10 LO MEJOR")
                AlbumListRow(albums, onOpenAlbum)

                Spacer(Modifier.height(24.dp))

                DescripcionLista("Clásicos que nunca fallan")
                AlbumListRow(albums, onOpenAlbum)

                Spacer(Modifier.height(24.dp))

                // 🔹 Botón de discografía
                Button(
                    onClick = onSeeAll,
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Ver toda la discografía", fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
private fun AlbumListRow(
    albums: List<com.example.proyecto_movil.data.AlbumUI>,
    onOpenAlbum: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(albums) { alb ->
            FotoAlbumArtistaPeque(
                alb.coverRes,
                modifier = Modifier.clickable { onOpenAlbum(alb.id) }
            )
        }
    }
}

@Preview(name = "Content Light", showSystemUi = true)
@Composable
fun ContentPreviewLight() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Content()
        }
    }
}

@Preview(name = "Content Dark", showSystemUi = true)
@Composable
fun ContentPreviewDark() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Content()
        }
    }
}
