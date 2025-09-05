package com.example.proyecto_movil.screen.mainScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.UserUI
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.ReviewRepository
import com.example.proyecto_movil.data.local.UserRepository
import com.example.proyecto_movil.uiViews.profile.ProfileUiEvent
import com.example.proyecto_movil.uiViews.profile.ProfileViewModel

@Composable
fun UserProfileScreen(
    user: UserUI,
    reviews: List<ReviewInfo>,
    onBackClick: () -> Unit = {},
    onAlbumClick: (AlbumUI) -> Unit = {},
    onReviewClick: (ReviewInfo) -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onEditProfile: () -> Unit = {}
) {
    val vm: ProfileViewModel = viewModel()
    val state by vm.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) { vm.onEvent(ProfileUiEvent.OnLoad) }

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
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
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { onBackClick() }
                    )
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuración",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { onSettingsClick() }
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.6f)
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = user.profilePic),
                    contentDescription = user.username,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = user.username,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${user.followers} seguidores • ${user.following} siguiendo",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = {
                        onEditProfile()
                        vm.onEvent(ProfileUiEvent.OnSaveProfile)
                    },
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Editar perfil", color = MaterialTheme.colorScheme.onPrimaryContainer)
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (user.playlists.isNotEmpty()) {
                    Text(
                        "Tus álbumes favoritos",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        items(user.playlists.first().albums.size) { index ->
                            val album = user.playlists.first().albums[index]
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
                                Text(
                                    album.title,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    fontSize = 14.sp
                                )
                                Text(
                                    album.artist.name,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "Tus reseñas",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    reviews.forEach { review ->
                        ReviewItem(review = review, onReviewClick = onReviewClick)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver reseñas y playlists", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }

        if (state.isLoading || state.isRefreshing) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }
    }
}

@Composable
private fun ReviewItem(review: ReviewInfo, onReviewClick: (ReviewInfo) -> Unit) {
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
            Text(
                review.album.title.uppercase(),
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                review.album.artist.name.uppercase(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
            OutlinedButton(
                onClick = { onReviewClick(review) },
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Text("Ver reseña", fontSize = 12.sp)
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
    }
}

@Preview(showBackground = true, name = "UserProfile Light", showSystemUi = true)
@Composable
fun UserProfilePreviewLight() {
    val user = UserRepository.users.first()
    Proyecto_movilTheme(useDarkTheme = false) {
        UserProfileScreen(
            user = user,
            reviews = ReviewRepository.getReviewsByUser(user.id)
        )
    }
}

@Preview(showBackground = true, name = "UserProfile Dark", showSystemUi = true)
@Composable
fun UserProfilePreviewDark() {
    val user = UserRepository.users.first()
    Proyecto_movilTheme(useDarkTheme = true) {
        UserProfileScreen(
            user = user,
            reviews = ReviewRepository.getReviewsByUser(user.id)
        )
    }
}
