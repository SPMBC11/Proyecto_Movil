package com.example.proyecto_movil.ui.Screens.AddReview

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(
    viewModel: AddReviewViewModel,
    modifier: Modifier = Modifier,
    onPublicarClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.navigateCancel, state.navigatePublished) {
        if (state.navigateCancel) {
            onCancelarClick()
            viewModel.consumeCancel()
        }
        if (state.navigatePublished) {
            onPublicarClick()
            viewModel.consumePublished()
        }
    }

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
                coverRes = state.albumCoverRes,
                title = state.albumTitle,
                artist = state.albumArtist,
                year = state.albumYear
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
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 25.dp)
                )
                ReadOnlyField(
                    value = state.dateString,
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
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 9.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ReadOnlyField(
                        value = "${state.scorePercent}%",
                        modifier = Modifier.width(120.dp)
                    )
                    Image(
                        painter = painterResource(id = if (state.liked) R.drawable.coralleno else R.drawable.coravacio),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickableNoRipple { viewModel.toggleLike() }
                    )
                }
            }

            SectionTitle(title = stringResource(id = R.string.agrega_resena))

            OutlinedTextField(
                value = state.reviewText,
                onValueChange = viewModel::updateReviewText,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                placeholder = { Text("Aquí puedes agregar una reseña...") }
            )

            if (state.showMessage) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.weight(1f))

            ActionButtonsRow(
                leftText = stringResource(id = R.string.cancelar_resena),
                rightText = stringResource(id = R.string.publicar_resena),
                onLeftClick = { viewModel.onCancelClicked() },
                onRightClick = { viewModel.onPublishClicked() },
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
            val vm = remember { AddReviewViewModel() }
            AddReviewScreen(viewModel = vm)
        }
    }
}

@Preview(name = "AddReview Dark", showSystemUi = true)
@Composable
fun AddReviewScreenDarkPreview() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { AddReviewViewModel() }
            AddReviewScreen(viewModel = vm)
        }
    }
}