package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ReviewRepository
import com.example.proyecto_movil.data.local.UserRepository
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme

@Composable
fun UserProfileScreen(
    username: String,
    bio: String,
    followers: Int,
    following: Int,
    profilePicRes: Int,
    favoriteAlbums: List<com.example.proyecto_movil.data.AlbumUI>,
    reviews: List<ReviewInfo>,
    onAlbumClick: (com.example.proyecto_movil.data.AlbumUI) -> Unit,
    onReviewClick: (ReviewInfo) -> Unit,
    onSettingsClick: () -> Unit,
    onEditProfile: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // 👉 Fondo
        Image(
            painter = painterResource(id = R.drawable.fondocriti),
            contentDescription = stringResource(id = R.string.fondo_degradado),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Atrás",
                        tint = Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { /* onBack */ }
                    )
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuración",
                        tint = Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { onSettingsClick() }
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto + datos
                Image(
                    painter = painterResource(id = profilePicRes),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = username, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color.White)
                Text(text = "$followers seguidores • $following siguiendo", color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(onClick = onEditProfile, shape = RoundedCornerShape(50)) {
                    Text("Editar perfil", color = Color.White)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Álbumes favoritos
                Text("Tus álbumes favoritos", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(vertical = 16.dp)) {
                    items(favoriteAlbums.size) { index ->
                        val album = favoriteAlbums[index]
                        Column(
                            modifier = Modifier
                                .width(120.dp)
                                .clickable { onAlbumClick(album) },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = album.coverRes),
                                contentDescription = album.title,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(album.title, color = Color.White, fontWeight = FontWeight.Bold, maxLines = 1, fontSize = 14.sp)
                            Text(album.artist.name, color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Reseñas
                Text("Tus reseñas", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    reviews.forEach { review ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onReviewClick(review) },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = review.album.coverRes),
                                contentDescription = review.album.title,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(RoundedCornerShape(6.dp))
                            )
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(review.album.title.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text(review.album.artist.name.uppercase(), color = Color.Gray, fontSize = 12.sp)
                                OutlinedButton(
                                    onClick = { onReviewClick(review) },
                                    shape = RoundedCornerShape(50),
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                                    modifier = Modifier.padding(top = 6.dp)
                                ) {
                                    Text("Ver reseña", fontSize = 12.sp, color = Color.White)
                                }
                            }
                            Spacer(Modifier.width(8.dp))
                            val scoreColor =
                                if (review.score >= 7) Color(0xFF2E7D32)
                                else if (review.score >= 5) Color(0xFFF9A825)
                                else Color(0xFFC62828)
                            Surface(color = scoreColor, shape = RoundedCornerShape(6.dp)) {
                                Text(
                                    text = "${(review.score * 10).toInt()}%",
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit), // asegúrate de tener este ícono
                                contentDescription = "Editar reseña",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Botón final
                Button(
                    onClick = { /* Navegar a reseñas + playlists */ },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver reseñas y playlists", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    Proyecto_movilTheme(useDarkTheme = true) {
        UserProfileScreen(
            username = "el.xokas",
            bio = "Streamer y crítico musical 🎶",
            followers = 17,
            following = 78,
            profilePicRes = R.drawable.xocas,
            favoriteAlbums = listOf(
                AlbumRepository.albums[11], // Gipsy Kings
                AlbumRepository.albums[12], // Abraham Mateo
                AlbumRepository.albums[13]  // Lánzalo
            ),
            reviews = ReviewRepository.reviews.take(3),
            onAlbumClick = {},
            onReviewClick = {},
            onSettingsClick = {},
            onEditProfile = {}
        )
    }
}
