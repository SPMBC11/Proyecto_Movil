package com.example.proyecto_movil.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.local.ReviewRepository
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme

@Composable
fun ReviewCard(
    review: ReviewInfo,
    modifier: Modifier = Modifier,
    onUserClick: (Int) -> Unit = {} // 👈 nuevo callback con id del usuario
) {
    val user = review.user
    val album = review.album
    val artist = album.artist

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // ---------- Usuario ----------
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = user.profilePic),
                    contentDescription = user.username,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable { onUserClick(user.id) } // 👈 navega al perfil
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = user.username,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(Modifier.height(12.dp))

            // ---------- Álbum + artista ----------
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = album.coverRes),
                    contentDescription = album.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        album.title,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        artist.name,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                    Text(
                        "(${album.year})",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // ---------- Contenido reseña ----------
            Text(
                text = review.content,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(8.dp))

            // ---------- Puntaje ----------
            val scoreColor = when {
                review.score >= 7 -> MaterialTheme.colorScheme.primary
                review.score >= 5 -> MaterialTheme.colorScheme.tertiary
                else -> MaterialTheme.colorScheme.error
            }

            Surface(
                color = scoreColor,
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "${(review.score * 10).toInt()}%",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}


/* ---------- Previews ---------- */

@Preview(showBackground = true, name = "ReviewCard Light")
@Composable
fun ReviewCardPreviewLight() {
    Proyecto_movilTheme(useDarkTheme = false) {
        ReviewCard(review = ReviewRepository.reviews.first())
    }
}

@Preview(showBackground = true, name = "ReviewCard Dark")
@Composable
fun ReviewCardPreviewDark() {
    Proyecto_movilTheme(useDarkTheme = true) {
        ReviewCard(review = ReviewRepository.reviews.first())
    }
}
