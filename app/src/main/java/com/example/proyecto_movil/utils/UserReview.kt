package com.example.proyecto_movil.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme

@Composable
fun UserReview(
    userImage: Int,
    userName: String,
    reviewText: String,
    isLiked: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            // 🔹 Cabecera
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = userImage),
                    contentDescription = "User profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(start = 20.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                )
                Text(
                    text = userName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp)
                )
            }

            // 🔹 Texto + corazón
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = reviewText,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(
                        id = if (isLiked) R.drawable.coralleno else R.drawable.coravacio
                    ),
                    contentDescription = if (isLiked) "Liked" else "Not liked",
                    tint = if (isLiked) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(start = 8.dp)
                )
            }
        }
    }
}


@Preview(name = "UserReview Light")
@Composable
fun UserReviewLightPreview() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            UserReview(
                userImage = R.drawable.tyler,
                userName = "Tyler, The Creator",
                reviewText = "This album is a masterpiece, the production is just insane! A timeless classic that everyone should listen to.",
                isLiked = true
            )
        }
    }
}

@Preview(name = "UserReview Dark")
@Composable
fun UserReviewDarkPreview() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            UserReview(
                userImage = R.drawable.tyler,
                userName = "Tyler, The Creator",
                reviewText = "This album is a masterpiece, the production is just insane! A timeless classic that everyone should listen to.",
                isLiked = false
            )
        }
    }
}

