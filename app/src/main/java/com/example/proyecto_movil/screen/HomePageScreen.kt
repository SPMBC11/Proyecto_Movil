package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_movil.R
import com.example.proyecto_movil.utils.recursos.AlbumUi
import com.example.proyecto_movil.utils.recursos.ArtistUI

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAlbumClick: (AlbumUi) -> Unit = {},     // 👉 Se invoca al tocar un álbum
    onHomeClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Fondo
        Image(
            painter = painterResource(id = R.drawable.fondocriti),
            contentDescription = stringResource(id = R.string.fondo_degradado),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 52.dp, start = 16.dp, end = 16.dp, bottom = 72.dp)
        ) {
            HeaderSection()

            Spacer(Modifier.height(12.dp))

            SearchBar()

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                item {
                    SectionRow(
                        title = "Novedades",
                        albums = listOf(
                            AlbumUi(1, R.drawable.tyler_dttg, "DON'T TAP THE GLASS", "2024",
                                ArtistUI(1, "Tyler, The Creator", "Pop", "Tyler, The Creator")),
                            AlbumUi(2, R.drawable.mcmiller, "CIRCLES", "2020",
                                ArtistUI(2, "Mac Miller", "Hip Hop", "Mac Miller")),
                            AlbumUi(3, R.drawable.feid, "FERXXO VOL 10: SAGRADO", "2024",
                                ArtistUI(3, "Feid", "Reggaeton", "Feid"))
                        ),
                        onAlbumClick = onAlbumClick
                    )
                }
                item {
                    SectionRow(
                        title = "Nuevo entre amigos",
                        albums = listOf(
                            AlbumUi(4, R.drawable.bogota_deluxe, "BOGOTÁ (DELUXE)", "2023",
                                ArtistUI(4, "Andrés Cepeda", "Balada", "Andrés Cepeda")),
                            AlbumUi(5, R.drawable.epistolares, "EPISTOLARES+", "2024",
                                ArtistUI(5, "AKRIILA", "Hip Hop", "AKRIILA")),
                            AlbumUi(6, R.drawable.babylon, "BABYLON CLUB", "2024",
                                ArtistUI(6, "Danny Ocean", "Pop", "Danny Ocean"))
                        ),
                        onAlbumClick = onAlbumClick
                    )
                }
                item {
                    SectionRow(
                        title = "Popular entre amigos",
                        albums = listOf(
                            AlbumUi(7, R.drawable.swag, "SWAG", "2013",
                                ArtistUI(7, "Justin Bieber", "Pop", "Justin Bieber")),
                            AlbumUi(8, R.drawable.billie, "HIT ME HARD AND SOFT", "2024",
                                ArtistUI(8, "Billie Eilish", "Pop", "Billie Eilish")),
                            AlbumUi(9, R.drawable.dbtmf, "DbTmF", "2024",
                                ArtistUI(9, "Bad Bunny", "Reggaeton", "Bad Bunny"))
                        ),
                        onAlbumClick = onAlbumClick
                    )
                }
            }
        }

        // Barra inferior
        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onHomeClick = onHomeClick,
            onProfileClick = onProfileClick
        )
    }
}

/* ---------- Subcomponentes ---------- */

@Composable
private fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.avatar_demo),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(
                    text = "Bienvenido de vuelta",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Juan",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp
                )
            }
        }

        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = stringResource(id = R.string.ajustes_icon),
            modifier = Modifier
                .padding(30.dp)
                .size(30.dp),
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = null, tint = Color.White)
        },
        placeholder = {
            Text(
                text = "Busca entre más de 5 millones de álbumes",
                color = Color.White.copy(alpha = 0.75f)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(26.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF0AAAC0),
            unfocusedBorderColor = Color(0xFF0AAAC0),
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    )
}

@Composable
private fun SectionRow(
    title: String,
    albums: List<AlbumUi>,
    onAlbumClick: (AlbumUi) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 12.dp, start = 4.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(albums) { album ->
                AlbumCard(album = album) { onAlbumClick(album) }  // 👉 pasa el álbum tocado
            }
        }
    }
}

@Composable
private fun AlbumCard(
    album: AlbumUi,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(130.dp)
            .clickable { onClick() }, // 👉 el toque en toda la card dispara onAlbumClick(album)
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = album.coverRes),
            contentDescription = album.title,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = album.title,
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        if (album.artist.name.isNotBlank()) {
            Text(
                text = album.artist.name,
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(Color(0xFF0C0C0C).copy(alpha = 0.6f))
            .padding(vertical = 10.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(
            icon = { Icon(Icons.Filled.Home, null, tint = Color(0xFF06A0B5)) },
            label = "Home",
            active = true,
            onClick = onHomeClick
        )
        BottomBarItem(
            icon = { Icon(Icons.Filled.Person, null, tint = Color.White) },
            label = "Perfil",
            active = false,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun BottomBarItem(
    icon: @Composable () -> Unit,
    label: String,
    active: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier.size(26.dp),
            contentAlignment = Alignment.Center
        ) { icon() }
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            color = if (active) Color(0xFF06A0B5) else Color.White,
            fontSize = 14.sp,
            fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

/* ---------- Wrapper opcional con navegación simple ---------- */
/* Úsalo si quieres que el Home navegue sin pasar lambdas desde afuera */
@Composable
fun HomeScreenRoute(navController: NavController) {
    HomeScreen(
        onAlbumClick = { album ->
            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.set("album", album)
            navController.navigate("albumReview")
        },
        onHomeClick = { /* no-op */ },
        onProfileClick = { /* navController.navigate("profile") */ }
    )
}

/* ---------- Preview ---------- */
@Preview(showBackground = true, name = "HomeScreen Preview")
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    com.example.proyecto_movil.ui.theme.Proyecto_movilTheme {
        HomeScreenRoute(navController)
    }
}
