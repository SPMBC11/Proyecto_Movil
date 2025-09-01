package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme

@Composable
fun BackGroundImage(
    modifier: Modifier = Modifier
) {
    // 👇 Si el fondo es claro, usa logo oscuro; si es oscuro, usa logo blanco
    val logoRes = if (MaterialTheme.colorScheme.background.luminance() > 0.5f) {
        R.drawable.logo_negro // tu versión oscura (para fondo claro)
    } else {
        R.drawable.logo // tu versión blanca (para fondo oscuro)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = logoRes),
                contentDescription = stringResource(R.string.critichord),
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.critichord),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BackGroundImagePreviewLight() {
    Proyecto_movilTheme(useDarkTheme = false) {
        BackGroundImage()
    }
}

@Preview(showBackground = true)
@Composable
fun BackGroundImagePreviewDark() {
    Proyecto_movilTheme(useDarkTheme = true) {
        BackGroundImage()
    }
}
