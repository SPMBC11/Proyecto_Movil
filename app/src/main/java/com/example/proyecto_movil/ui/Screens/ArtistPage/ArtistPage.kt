package com.example.proyecto_movil.ui.Screens.ArtistPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.*

@Composable
fun ArtistPage(
    artistId: Int? = null,
    viewModel: ArtistPageViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onOpenAlbum: (Int) -> Unit = {},
    onSeeAll: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    // Cargar artista cuando cambia el id
    LaunchedEffect(artistId) { viewModel.setArtist(artistId) }

    // Navegaciones / eventos
    LaunchedEffect(state.navigateBack, state.navigateSeeAll, state.openAlbumId) {
        if (state.navigateBack) {
            onBack()
            viewModel.consumeBack()
        }
        if (state.navigateSeeAll) {
            onSeeAll()
            viewModel.consumeSeeAll()
        }
        state.openAlbumId?.let { id ->
            onOpenAlbum(id)
            viewModel.consumeOpenAlbum()
        }
    }

    val backgroundRes = if (isSystemInDarkTheme()) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 16.dp, end = 16.dp)
        ) {
            // TopBar: back + settings
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.onBackClicked() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                SettingsIcon()
            }

            Spacer(Modifier.height(20.dp))

            // Contenido
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = state.artistProfileRes,
                    contentDescription = "Foto de ${state.artistName}",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )


                Spacer(Modifier.height(10.dp))
                TituloArtista(state.artistName)

                Spacer(Modifier.height(16.dp))
                SeguidoresCantante(state.followersText)
                Spacer(Modifier.height(16.dp))
                SeguidoresCantante(state.globalScoreText)
                TextoReseñas(state.reviewsExtraText)

                Spacer(Modifier.height(24.dp))
                TituloAlbum("Álbumes")

                Spacer(Modifier.height(12.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.albums) { alb ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable { viewModel.onAlbumClicked(alb.id) }
                        ) {
                            AsyncImage(
                                model = alb.coverRes,                           // String (URL)
                                contentDescription = "Portada: ${alb.title}",
                                modifier = Modifier.size(140.dp),
                                contentScale = ContentScale.Crop
                            )

                            TituloAlbumes(alb.title)
                            Spacer(Modifier.height(4.dp))
                            FechaAlbum(alb.year)
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.onSeeAllClicked() },
                    shape = RoundedCornerShape(25.dp),
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

/* ==== PREVIEWS ==== */

@Preview(name = "ArtistPage Light", showSystemUi = true)
@Composable
fun ArtistPageLightPreview() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { ArtistPageViewModel().also { it.setArtist(null) } }
            ArtistPage(viewModel = vm)
        }
    }
}

@Preview(name = "ArtistPage Dark", showSystemUi = true)
@Composable
fun ArtistPageDarkPreview() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { ArtistPageViewModel().also { it.setArtist(null) } }
            ArtistPage(viewModel = vm)
        }
    }
}
