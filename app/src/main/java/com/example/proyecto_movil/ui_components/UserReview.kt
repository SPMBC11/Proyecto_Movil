package com.example.proyecto_movil.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UserReview(
    userImage: Int,
    userName: String,
    reviewText: String,
    isLiked: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = userImage),
                contentDescription = "User profile picture",
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 20.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
            )
            Text(
                text = userName,
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = reviewText,
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(
                    id = if (isLiked) R.drawable.coralleno else R.drawable.coravacio
                ),
                contentDescription = if (isLiked) "Full heart icon" else "Empty heart icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserReviewPreview() {
    // Ejemplo de uso para la vista previa
    UserReview(
        userImage = R.drawable.tyler,
        userName = "Tyler, The Creator",
        reviewText = "This album is a masterpiece, the production is just insane! A timeless classic that everyone should listen to.",
        isLiked = true
    )
}