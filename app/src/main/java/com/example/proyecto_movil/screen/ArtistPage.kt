package com.example.proyecto_movil.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.local.Catalog
import com.example.proyecto_movil.ui.utils.FechaAlbum
import com.example.proyecto_movil.ui.utils.FotoAlbumArtista
import com.example.proyecto_movil.ui.utils.FotoPerfilArtista
import com.example.proyecto_movil.ui.utils.SeguidoresCantante
import com.example.proyecto_movil.ui.utils.TextoReseñas
import com.example.proyecto_movil.ui.utils.TituloAlbum
import com.example.proyecto_movil.ui.utils.TituloAlbumes
import com.example.proyecto_movil.ui.utils.TituloArtista
import com.example.proyecto_movil.utils.ScreenBackground
import com.example.proyecto_movil.utils.SettingsIcon

@Composable
fun Artistpage(
    artistId: Int? = null,                 // ← opcional para soportar ambas rutas
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onOpenAlbum: (Int) -> Unit = {},
    onSeeAll: () -> Unit = {}
) {
    // Resuelve artista a mostrar
    val artist = artistId?.let { Catalog.artistById(it) } ?: Catalog.artists.firstOrNull()
    if (artist == null) {
        // Si no hay datos, simplemente no dibuja nada (o podrías hacer onBack())
        return
    }
    val albums = Catalog.albumsByArtist(artist.id)

    ScreenBackground(backgroundRes = R.drawable.fondocriti, modifier = modifier) {
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
                        tint = colorResource(id = R.color.white)
                    )
                }
                SettingsIcon()
            }

            Spacer(Modifier.height(20.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                FotoPerfilArtista(artist.photoRes)
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
                        containerColor = colorResource(id = R.color.azulCriti),
                        contentColor = Color.White
                    )
                ) {
                    Text("Ver toda la discografía", fontSize = 14.sp)
                }
            }
        }
    }
}
