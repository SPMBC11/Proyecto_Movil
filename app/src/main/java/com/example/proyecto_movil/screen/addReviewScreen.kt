package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.utils.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(
    modifier: Modifier = Modifier,
    onPublicarClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {}
) {
    ScreenBackground(backgroundRes = R.drawable.fondocriti, modifier = modifier) {
        // Es un BoxScope: aquí sí podemos alinear
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
                coverRes = R.drawable.mcmiller,
                title = stringResource(id = R.string.titulo_album),
                artist = stringResource(id = R.string.artista_album),
                year = stringResource(id = R.string.ano_album)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.fecha_resena),
                    color = androidx.compose.ui.graphics.Color.White,
                    fontSize = androidx.compose.ui.unit.TextUnit.Unspecified,
                    modifier = Modifier.padding(start = 25.dp)
                )
                ReadOnlyField(
                    value = "7 de Julio de 2025",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 45.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.puntaje_resena),
                    color = androidx.compose.ui.graphics.Color.White,
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

            SectionTitle(
                title = stringResource(id = R.string.agrega_resena)
            )

            ReadOnlyField(
                value = "Aqui puedes agregar una reseña...",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp)
            )

            Spacer(Modifier.weight(1f))

            ActionButtonsRow(
                leftText = stringResource(id = R.string.cancelar_resena),
                rightText = stringResource(id = R.string.publicar_resena),
                onLeftClick = onCancelarClick,
                onRightClick = onPublicarClick,
                leftColor = colorResource(id = R.color.grisCriti),
                rightColor = colorResource(id = R.color.azulCriti)
            )
        }
    }
}

@Preview(showBackground = true, name = "AddReviewScreen Preview")
@Composable
fun AddReviewScreenPreview() {
    AddReviewScreen()
}
