package com.example.proyecto_movil.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
fun BackGroundImage(
    modifier: Modifier = Modifier
) {
    val darkTheme = isSystemInDarkTheme()
    val logoRes = if (darkTheme) R.drawable.logo else R.drawable.logo_negro
    val backgroundRes = if (darkTheme) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
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
