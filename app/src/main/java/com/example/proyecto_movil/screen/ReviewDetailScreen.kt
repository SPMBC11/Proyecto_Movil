package com.example.proyecto_movil.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.data.ReviewInfo

@Composable
fun ReviewDetailScreen(
    review: ReviewInfo,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Reseña de ${review.user.username}")
        Spacer(Modifier.height(10.dp))
        Text(text = review.content)
        Spacer(Modifier.height(10.dp))
        Text(text = "Puntuación: ${review.score}")
    }
}
