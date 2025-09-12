package com.example.proyecto_movil.ui.Screens.AddReview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.ui.utils.ScreenBackground

@Composable
fun AddReviewScreen(
    viewModel: AddReviewViewModel,
    albumList: List<AlbumUI>,
    onCancel: () -> Unit,
    onPublished: (AlbumUI, String, Int, Boolean) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light
    val scrollState = rememberScrollState()

    ScreenBackground(backgroundRes = backgroundRes) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Dropdown para seleccionar álbum
            AlbumDropdown(
                albums = albumList,
                selectedTitle = state.albumTitle,
                onAlbumSelected = { album -> viewModel.updateAlbum(album) }
            )

            // Campo de texto para la reseña
            OutlinedTextField(
                value = state.reviewText,
                onValueChange = { viewModel.updateReviewText(it) },
                label = { Text("Escribe tu reseña") },
                modifier = Modifier.fillMaxWidth()
            )

            // Slider para puntaje
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Puntaje: ${state.scorePercent}%")
                Slider(
                    value = state.scorePercent.toFloat(),
                    onValueChange = { viewModel.updateScore(it.toInt()) },
                    valueRange = 0f..100f,
                    steps = 10
                )
            }

            // Checkbox para like
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = state.liked,
                    onCheckedChange = { viewModel.toggleLike() }
                )
                Text(if (state.liked) "¡Me gustó!" else "No me gustó")
            }

            // Mensaje de error si no hay texto
            if (state.showMessage) {
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(onClick = { viewModel.onCancelClicked() }) {
                    Text("Cancelar")
                }
                Button(onClick = { viewModel.onPublishClicked() }) {
                    Text("Publicar")
                }
            }
        }
    }

    // Navegación al cancelar
    LaunchedEffect(state.navigateCancel) {
        if (state.navigateCancel) {
            onCancel()
            viewModel.consumeCancel()
        }
    }

    // Navegación al publicar
    LaunchedEffect(state.navigatePublished) {
        if (state.navigatePublished) {
            val selectedAlbum = albumList.find { it.title == state.albumTitle }
            if (selectedAlbum != null) {
                onPublished(selectedAlbum, state.reviewText, state.scorePercent, state.liked)
            }
            viewModel.consumePublished()
        }
    }
}

/* ---------- Dropdown para álbumes ---------- */
@Composable
fun AlbumDropdown(
    albums: List<AlbumUI>,
    selectedTitle: String,
    onAlbumSelected: (AlbumUI) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selectedTitle.ifBlank { "Selecciona un álbum" })
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            albums.forEach { album ->
                DropdownMenuItem(
                    text = { Text(album.title) },
                    onClick = {
                        onAlbumSelected(album)
                        expanded = false
                    }
                )
            }
        }
    }

}

