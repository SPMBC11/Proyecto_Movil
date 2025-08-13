package com.example.proyecto_movil.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun HomePageScreen(

    onBack: () -> Unit = {},
){



    val bg = Color(0xFF0E0E0E)
    val card = Color(0xFF151515)
    val accent = Color(0xFF20D0C2)
    val fieldText = Color(0xFFEDEDED)
    val hint = Color(0xFF9A9A9A)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(horizontal = 20.dp)
    ){
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 8.dp)
        ) {
            Icon(Icons.Default.Logout, contentDescription = "Atrás", tint = Color.White)
        }


    }
    Text("Bienvenido de vuelta Juan")
}

@Composable
@Preview(showBackground = true)
fun HomePageScreenPreview(){
    HomePageScreen()
}
