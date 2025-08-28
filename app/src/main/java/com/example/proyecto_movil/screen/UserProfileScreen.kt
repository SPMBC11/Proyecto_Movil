package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.local.FalseAlbumProfRepository
import com.example.proyecto_movil.data.local.FalseReviewProfRepository
import com.example.proyecto_movil.data.local.FalseProfileInfoRepository
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.utils.ScreenBackground
import com.example.proyecto_movil.utils.SettingsIcon
import com.example.proyecto_movil.utils.SectionTitle

@Composable
fun UserProfileScreen(isCurrentUserProfile: Boolean, modifier: Modifier = Modifier) {
    val profileInfo = FalseProfileInfoRepository.profile
    val favoriteAlbums = FalseAlbumProfRepository.albums
    val userReviews = FalseReviewProfRepository.reviews

    ScreenBackground(backgroundRes = R.drawable.fondocriti, modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = colorResource(id = R.color.white),
                modifier = Modifier.size(30.dp)
            )
            SettingsIcon()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, start = 16.dp, end = 16.dp)
        ) {

            item {
                Spacer(modifier = Modifier.height(20.dp))

                ProfileHeader(
                    userImageRes = profileInfo.imageId,
                    userName = profileInfo.username,
                    followers = profileInfo.followers,
                    following = profileInfo.following,
                    isCurrentUserProfile = isCurrentUserProfile
                )

                Spacer(modifier = Modifier.height(10.dp))

                SectionTitle(title = "Álbumes favoritos")

                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    items(favoriteAlbums) { album ->
                        AlbumCard(
                            coverRes = album.imageId,
                            title = album.title,
                            artist = album.artist
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                SectionTitle(title = "Reseñas")
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(userReviews) { review ->
                ReviewItem(
                    coverRes = review.imageId,
                    title = review.title,
                    artist = review.artist,
                    score = review.score,
                    isLowScore = review.isLowScore
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                        .height(35.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.azulCriti)
                    )
                ) {
                    Text(
                        text = "Ver todas las reseñas",
                        color = colorResource(id = R.color.white),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(
    userImageRes: Int,
    userName: String,
    followers: Int,
    following: Int,
    isCurrentUserProfile: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = userImageRes),
            contentDescription = "Imagen de perfil de $userName",
            modifier = Modifier
                .size(119.dp)
                .clip(CircleShape)
                .border(2.dp, colorResource(id = R.color.black), CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = userName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )
            Row {
                Text(
                    text = "$followers seguidores",
                    color = colorResource(id = R.color.white).copy(alpha = 0.7f)
                )
                Text(
                    text = " • ",
                    color = colorResource(id = R.color.white).copy(alpha = 0.7f)
                )
                Text(
                    text = "$following siguiendo",
                    color = colorResource(id = R.color.white).copy(alpha = 0.7f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (isCurrentUserProfile) {
                OutlinedButton(
                    onClick = { /* TODO: Lógica para seguir */ },
                    modifier = Modifier.height(30.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = colorResource(id = R.color.white),
                        containerColor = Color.Transparent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp)
                ) {
                    Text(text = "Editar Perfil", fontSize = 12.sp)
                }
            } else {
                OutlinedButton(
                    onClick = { /* TODO: Lógica para seguir */ },
                    modifier = Modifier.height(30.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = colorResource(id = R.color.white),
                        containerColor = Color.Transparent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp)
                ) {
                    Text(text = "Seguir", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun AlbumCard(
    coverRes: Int,
    title: String,
    artist: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        Image(
            painter = painterResource(id = coverRes),
            contentDescription = "Portada del álbum $title",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center
        )
        Text(
            text = artist,
            fontSize = 10.sp,
            color = colorResource(id = R.color.white).copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ReviewItem(
    coverRes: Int,
    title: String,
    artist: String,
    score: String,
    isLowScore: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = coverRes),
            contentDescription = "Portada del álbum",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(64.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )
            Text(
                text = artist,
                fontSize = 14.sp,
                color = colorResource(id = R.color.white).copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = { /* TODO */ },
                modifier = Modifier.height(30.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colorResource(id = R.color.white),
                    containerColor = Color.Transparent
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp)
            ) {
                Text(text = "Ver reseña", fontSize = 12.sp)
            }
        }

        ScoreBadge(score = score, isLowScore = isLowScore)
    }
}

@Composable
fun ScoreBadge(
    score: String,
    isLowScore: Boolean
) {
    val backgroundColor = if (isLowScore) {
        colorResource(id = R.color.cafePuntCriti)
    } else {
        colorResource(id = R.color.verdePuntCriti)
    }

    Box(
        modifier = Modifier
            .width(45.dp)
            .height(30.dp)
            .background(backgroundColor, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = score,
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true, name = "My Profile Preview")
@Composable
fun MyProfileScreenPreview() {
    Proyecto_movilTheme {
        UserProfileScreen(isCurrentUserProfile = true)
    }
}

@Preview(showBackground = true, name = "Other User Profile Preview")
@Composable
fun OtherUserProfileScreenPreview() {
    Proyecto_movilTheme {
        UserProfileScreen(isCurrentUserProfile = false)
    }
}