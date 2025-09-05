package com.example.proyecto_movil.screen.components

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
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.utils.ScreenBackground
import com.example.proyecto_movil.utils.SettingsIcon
import com.example.proyecto_movil.ui.utils.*

@Composable
fun ContentUser(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onOpenAlbum: (Int) -> Unit = {}
) {
    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    // Traer los álbumes del repositorio real
    val albums = AlbumRepository.albums

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
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                TituloArtista("Contenido")
                SettingsIcon(
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                // ---------- Reseñas ----------
                TituloAlbum("Reseñas")
                Spacer(Modifier.height(12.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    items(albums.take(3)) { alb ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { onOpenAlbum(alb.id) }
                        ) {
                            FotoAlbumArtistaPeque(alb.coverRes)
                            Column(Modifier.padding(start = 8.dp)) {
                                TituloAlbumPeque(alb.title)
                                Spacer(Modifier.height(4.dp))
                                TituloArtistaPeque(alb.artist.name)
                                BotonEscribir()
                            }
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                // ---------- Listas ----------
                TituloAlbum("Listas")
                Spacer(Modifier.height(15.dp))

                DescripcionLista("Canciones que atentaron contra mi estabilidad emocional")
                AlbumListRow(albums)

                Spacer(Modifier.height(24.dp))

                DescripcionLista("10/10 LO MEJOR")
                AlbumListRow(albums)

                Spacer(Modifier.height(24.dp))

                DescripcionLista("Los peores álbumes de 2025")
                AlbumListRow(albums)

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun AlbumListRow(albums: List<AlbumUI>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(albums) { alb ->
            FotoAlbumArtistaPeque(alb.coverRes)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentUserPreviewLight() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        ContentUser()
    }
}

@Preview(showBackground = true)
@Composable
fun ContentUserPreviewDark() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        ContentUser()
    }
}
