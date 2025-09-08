package com.example.proyecto_movil.ui.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.local.ReviewRepository
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme

@Composable
fun ReviewDetailScreen(
    review: ReviewInfo,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            //  TopBar con botón de volver
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(Modifier.height(24.dp))

            //  Contenido de la reseña
            Text(
                text = "Reseña de ${review.user.username}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = review.content,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Puntuación: ${review.score}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

/* ---------- Previews ---------- */

@Preview(name = "ReviewDetail Light", showSystemUi = true)
@Composable
fun ReviewDetailScreenLightPreview() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface {
            ReviewDetailScreen(
                review = ReviewRepository.reviews.first(),
                onBack = {}
            )
        }
    }
}

@Preview(name = "ReviewDetail Dark", showSystemUi = true)
@Composable
fun ReviewDetailScreenDarkPreview() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface {
            ReviewDetailScreen(
                review = ReviewRepository.reviews.first(),
                onBack = {}
            )
        }
    }
}
