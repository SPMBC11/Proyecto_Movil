package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.utils.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(
    modifier: Modifier = Modifier,
    onPublicarClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {}
) {
    // 🔹 Fondo dinámico según el tema
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundRes = if (isDarkTheme) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        SettingsIcon(modifier = Modifier.align(Alignment.TopEnd))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 55.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleBar(text = stringResource(id = R.string.titulo_agreresenas))

            Spacer(Modifier.height(32.dp))

            AlbumHeader(
                coverRes = R.drawable.circles,
                title = stringResource(id = R.string.titulo_album),
                artist = stringResource(id = R.string.artista_album),
                year = stringResource(id = R.string.ano_album)
            )

            // 📅 Fecha reseña
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.fecha_resena),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 25.dp)
                )
                ReadOnlyField(
                    value = "7 de Julio de 2025",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 45.dp)
                )
            }

            // ⭐ Puntaje reseña
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.puntaje_resena),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 9.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ReadOnlyField(
                        value = "97%",
                        modifier = Modifier.width(120.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.coravacio),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            // ✍️ Campo reseña
            SectionTitle(
                title = stringResource(id = R.string.agrega_resena)
            )

            OutlinedTextField(
                value = "Aquí puedes agregar una reseña...",
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                )
            )

            Spacer(Modifier.weight(1f))

            // 🔹 Botones
            ActionButtonsRow(
                leftText = stringResource(id = R.string.cancelar_resena),
                rightText = stringResource(id = R.string.publicar_resena),
                onLeftClick = onCancelarClick,
                onRightClick = onPublicarClick,
                leftColor = MaterialTheme.colorScheme.secondary,
                rightColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(name = "AddReview Light", showSystemUi = true)
@Composable
fun AddReviewScreenLightPreview() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AddReviewScreen()
        }
    }
}

@Preview(name = "AddReview Dark", showSystemUi = true)
@Composable
fun AddReviewScreenDarkPreview() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AddReviewScreen()
        }
    }
}
