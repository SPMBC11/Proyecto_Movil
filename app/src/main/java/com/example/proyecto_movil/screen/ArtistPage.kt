package com.example.proyecto_movil.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
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

// Modelo simple para pintar álbumes
data class ArtistAlbumUi(
    val id: Int,
    @DrawableRes val coverRes: Int,
    val title: String,
    val year: String
)

@Composable
fun ArtistPage(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onOpenAlbum: (Int) -> Unit = {},
    onSeeAll: () -> Unit = {}
) {
    ScreenBackground(backgroundRes = R.drawable.fondocriti, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 16.dp, end = 16.dp)
        ) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = colorResource(id = R.color.white),
                        modifier = Modifier.size(30.dp)
                    )
                }
                SettingsIcon()
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Contenido
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                FotoPerfilArtista(R.drawable.sabrina)

                Spacer(modifier = Modifier.height(10.dp))
                TituloArtista("Sabrina Carpenter")
                Spacer(modifier = Modifier.height(10.dp))

                SeguidoresCantante("17K seguidores")
                Spacer(modifier = Modifier.height(15.dp))

                SeguidoresCantante("Puntaje global")
                Spacer(modifier = Modifier.height(15.dp))
                TextoReseñas("de 4500 reseñas")

                Spacer(modifier = Modifier.height(32.dp))

                // Álbumes
                TituloAlbum("Álbumes:")
                Spacer(modifier = Modifier.height(12.dp))

                val albums = listOf(
                    ArtistAlbumUi(
                        id = 1,
                        coverRes = R.drawable.mansbestfriend,
                        title = "MAN`S BEST FRIEND",
                        year = "2025"
                    ),
                    ArtistAlbumUi(
                        id = 2,
                        coverRes = R.drawable.shortnsweet,
                        title = "SHORT N`SWEET",
                        year = "2024"
                    ),
                    ArtistAlbumUi(
                        id = 3,
                        coverRes = R.drawable.emailsicantsend,
                        title = "emails i can't send",
                        year = "2023"
                    )
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(albums) { album ->
                        Column(
                            modifier = Modifier
                                .clickable { onOpenAlbum(album.id) }
                                .padding(bottom = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            FotoAlbumArtista(album.coverRes)
                            TituloAlbumes(album.title)
                            Spacer(modifier = Modifier.height(5.dp))
                            FechaAlbum(album.year)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onSeeAll,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                        .height(40.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.azulCriti),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Ver toda la discografía",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArtistPage() {
    ArtistPage()
}
