package com.example.proyecto_movil.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R

@Composable
fun LogoApp(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "Logo critichord",
        modifier = Modifier.size(120.dp)
    )
}

@Composable
fun Registrate(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )
}

@Composable
fun Terminos(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 15.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}

@Composable
fun YatienesCuenta(
    texto: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Text(
        text = texto,
        fontSize = 15.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}

@Composable
fun CheckboxDatos(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                checkmarkColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Composable
fun SeguidoresCantante(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 15.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )
}

@Composable
fun TituloArtista(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )
}

@Composable
fun TextoReseñas(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}

@Composable
fun FotoAlbumArtista(
    idImage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(idImage),
        contentDescription = "Imagen del álbum",
        modifier = modifier
            .padding(8.dp)
            .size(100.dp)
    )
}

@Composable
fun FotoAlbumArtistaPeque(
    idImage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(idImage),
        contentDescription = "Imagen del álbum pequeña",
        modifier = modifier
            .padding(8.dp)
            .size(55.dp)
    )
}

@Composable
fun FotoPerfilArtista(
    idImage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(idImage),
        contentDescription = "Imagen de perfil",
        modifier = modifier
            .size(140.dp)
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun BotonEscribir(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface
) {
    Image(
        painter = painterResource(id = R.drawable.editar),
        contentDescription = "Editar",
        modifier = Modifier.size(15.dp)
    )
}

@Composable
fun TituloAlbum(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TituloArtistaPeque(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TituloAlbumPeque(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start),
        textAlign = TextAlign.Center
    )
}

@Composable
fun DescripcionLista(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TituloAlbumes(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier,
        textAlign = TextAlign.Start
    )
}

@Composable
fun FechaAlbum(
    texto: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BotonGuardar(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        Text(text)
    }
}

@Composable
fun BotonEditarImagen(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
    ) {
        Text(text)
    }
}
