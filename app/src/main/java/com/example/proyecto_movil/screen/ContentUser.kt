package com.example.proyecto_movil.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.local.Catalog
import com.example.proyecto_movil.ui.utils.BotonEscribir
import com.example.proyecto_movil.ui.utils.FotoAlbumArtistaPeque
import com.example.proyecto_movil.ui.utils.TituloAlbum
import com.example.proyecto_movil.ui.utils.TituloAlbumPeque
import com.example.proyecto_movil.ui.utils.TituloArtista
import com.example.proyecto_movil.ui.utils.DescripcionLista
import com.example.proyecto_movil.ui.utils.TituloArtistaPeque
import com.example.proyecto_movil.ui_components.ScreenBackground
import com.example.proyecto_movil.ui_components.SettingsIcon

@Composable
fun ContentUser(
    artistId: Int? = null,                 // ← opcional para soportar ambas rutas
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onOpenAlbum: (Int) -> Unit = {},
    onSeeAll: () -> Unit = {}
) {
    // Resuelve artista a mostrar
    val selectedArtist = artistId?.let { Catalog.artistById(it) }
    val albums = selectedArtist?.let { Catalog.albumsByArtist(it.id) } ?: Catalog.albums
    val headerTitle = selectedArtist?.name ?: "Todos"

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
                TituloArtista("contenido")
                SettingsIcon()
            }



            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                TituloAlbum("Reseñas")
                Spacer(Modifier.height(12.dp))

                LazyRow(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                    items(albums.take(2)) { alb ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable { onOpenAlbum(alb.id) }
                        ) {
                            Spacer(Modifier.height(4.dp))
                          Row (){
                              FotoAlbumArtistaPeque(alb.coverRes)
                              Column() {
                                  TituloAlbumPeque(alb.title)
                                  Spacer(Modifier.height(7.dp))
                                  Row(horizontalArrangement =
                                      Arrangement.spacedBy(8.dp)) {
                                      Spacer(Modifier.height(7.dp))
                                      TituloArtistaPeque(alb.artistName)
                                      BotonEscribir()
                                  }
                              }
                          }
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                    items(albums.drop(2).take(2)) { alb ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable { onOpenAlbum(alb.id) }
                        ) {
                            Spacer(Modifier.height(4.dp))
                            Row (){
                                FotoAlbumArtistaPeque(alb.coverRes)
                                Column() {
                                    TituloAlbumPeque(alb.title)
                                    Spacer(Modifier.height(7.dp))
                                    Row(horizontalArrangement =
                                        Arrangement.spacedBy(8.dp)) {
                                        Spacer(Modifier.height(7.dp))
                                        TituloArtistaPeque(alb.artistName)
                                        BotonEscribir()
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally, )) {
                    items(albums.drop(4).take(2)) { alb ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable { onOpenAlbum(alb.id) }
                        ) {
                            Spacer(Modifier.height(4.dp))
                            Row (){
                                FotoAlbumArtistaPeque(alb.coverRes)
                                Column() {
                                    TituloAlbumPeque(alb.title)
                                    Spacer(Modifier.height(7.dp))
                                    Row(horizontalArrangement =
                                        Arrangement.spacedBy(8.dp)) {
                                        Spacer(Modifier.height(7.dp))
                                        TituloArtistaPeque(alb.artistName)
                                        BotonEscribir()
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
                TituloAlbum("Listas")
                Spacer(Modifier.height(15.dp))
                DescripcionLista(
                    "Canciones que atentaron " +
                            "contra mi estabilidad emocional"
                )
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.Start),
                    contentPadding = PaddingValues(start = 0.dp, end = 0.dp)) {
                    items(albums) { alb ->
                            Row( modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                FotoAlbumArtistaPeque(alb.coverRes)
                            }
                        }
                    }
                Spacer(Modifier.height(24.dp))
                DescripcionLista(
                    "10/10 LO MEJOR")
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.Start),
                    contentPadding = PaddingValues(start = 0.dp, end = 0.dp)) {
                    items(albums) { alb ->
                        Row( modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            FotoAlbumArtistaPeque(alb.coverRes)
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
                DescripcionLista(
                    "Los peores albumes de 2025")
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.Start),
                    contentPadding = PaddingValues(start = 0.dp, end = 0.dp)) {
                    items(albums) { alb ->
                        Row( modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            FotoAlbumArtistaPeque(alb.coverRes)
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))

            }
        }
    }
    }

// aqui se usa la la preview del ContentUser
@Preview(showBackground = true)
@Composable
fun ContentUserPreview() {
    ContentUser()
}
