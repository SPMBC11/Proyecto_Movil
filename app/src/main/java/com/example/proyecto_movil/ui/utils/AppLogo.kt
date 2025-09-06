package com.example.proyecto_movil.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.example.proyecto_movil.R

@Composable
fun AppLogo(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.logo), // res/drawable/logo.png
        contentDescription = "Logo",
        modifier = modifier.size(96.dp)
    )
}
