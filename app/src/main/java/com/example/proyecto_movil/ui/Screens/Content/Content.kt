package com.example.proyecto_movil.ui.Screens.Content

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.*
import com.example.proyecto_movil.ui.utils.ScreenBackground
import com.example.proyecto_movil.ui.utils.SettingsIcon

@Composable
fun ContentScreen(
    viewModel: ContentViewModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onOpenAlbum: (Int) -> Unit = {},
    onSeeAll: () -> Unit = {},
    onEditAlbum: (Int) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    // Manejo de efectos de navegación
    LaunchedEffect(state.navigateBack, state.openAlbumId, state.seeAllDiscography, state.editAlbumId) {
        if (state.navigateBack) { onBack(); viewModel.consumeBack() }
        state.openAlbumId?.let { onOpenAlbum(it); viewModel.consumeOpenAlbum() }
        if (state.seeAllDiscography) { onSeeAll(); viewModel.consumeSeeAll() }
        state.editAlbumId?.let { onEditAlbum(it); viewModel.consumeEditAlbum() }
    }

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 16.dp, end = 16.dp)
        ) {
            // Top bar
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
                TituloArtista(state.headerTitle)
                SettingsIcon(tint = MaterialTheme.colorScheme.onSurface)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                // --------- Reseñas ----------
                TituloAlbum("Reseñas")
                Spacer(Modifier.height(12.dp))

                // Igual que tus pantallas: filas en LazyRow con carátula + textos;
                // si es propietario, muestra “Escribir” y botón de editar.
                state.albums.chunked(2).take(3).forEach { group ->
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        items(group) { alb ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { viewModel.onOpenAlbum(alb.id) }
                            ) {
                                FotoAlbumArtistaPeque(alb.coverRes)
                                Column(Modifier.padding(start = 8.dp)) {
                                    TituloAlbumPeque(alb.title)
                                    Spacer(Modifier.height(4.dp))
                                    TituloArtistaPeque(alb.artist.name)

                                    if (state.isOwner) {
                                        Spacer(Modifier.height(4.dp))
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            BotonEscribir()
                                            Spacer(Modifier.width(6.dp))
                                            IconButton(
                                                onClick = { viewModel.onEditAlbumClicked(alb.id) },
                                                modifier = Modifier.size(24.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Edit,
                                                    contentDescription = "Editar",
                                                    tint = MaterialTheme.colorScheme.onSurface
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                }

                // --------- Listas ----------
                TituloAlbum("Listas")
                Spacer(Modifier.height(15.dp))

                DescripcionLista("Canciones que atentaron contra mi estabilidad emocional")
                AlbumListRow(state.albums) { id -> viewModel.onOpenAlbum(id) }

                Spacer(Modifier.height(24.dp))

                DescripcionLista("10/10 LO MEJOR")
                AlbumListRow(state.albums) { id -> viewModel.onOpenAlbum(id) }

                Spacer(Modifier.height(24.dp))

                DescripcionLista(if (state.isOwner) "Los peores álbumes de 2025" else "Clásicos que nunca fallan")
                AlbumListRow(state.albums) { id -> viewModel.onOpenAlbum(id) }

                Spacer(Modifier.height(24.dp))

                // Botón “Ver toda la discografía” solo si hay artista (modo no propietario)
                if (!state.isOwner && state.artistId != null) {
                    Button(
                        onClick = { viewModel.onSeeAllClicked() },
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text("Ver toda la discografía")
                    }
                }
            }
        }
    }
}

/* Helpers internos */
@Composable
private fun AlbumListRow(
    albums: List<AlbumUI>,
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

/* ---------- Previews ---------- */
@Preview(name = "Content (Owner) Light", showSystemUi = true)
@Composable
fun ContentOwnerPreviewLight() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { ContentViewModel() }
            // Simula ContentUser
            LaunchedEffect(Unit) { vm.setInitial(artistId = null, isOwner = true) }
            ContentScreen(
                viewModel = vm
            )
        }
    }
}

@Preview(name = "Content (Artist) Dark", showSystemUi = true)
@Composable
fun ContentArtistPreviewDark() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { ContentViewModel() }
            // Simula Content por artista (no owner)
            LaunchedEffect(Unit) { vm.setInitial(artistId = AlbumRepository.albums.first().artist.id, isOwner = false) }
            ContentScreen(
                viewModel = vm
            )
        }
    }
}
