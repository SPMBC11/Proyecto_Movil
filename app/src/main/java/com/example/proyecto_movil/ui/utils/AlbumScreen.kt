package com.example.proyecto_movil.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext


// ✅ Fondo con imagen dinámico
@Composable
fun ScreenBackground(
    @DrawableRes backgroundRes: Int,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        content()
    }
}

// ✅ Icono de ajustes con color adaptable
@Composable
fun SettingsIcon(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface
) {
    Icon(
        imageVector = Icons.Filled.Settings,
        contentDescription = null,
        modifier = modifier
            .padding(30.dp)
            .size(30.dp),
        tint = tint
    )
}

// ✅ Título de barra superior
@Composable
fun TitleBar(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// ✅ Encabezado de álbum (titulo, artista, año)
@Composable
fun AlbumHeader(
    coverRes: String,
    title: String,
    artist: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(coverRes)          // ← tu URL
                .crossfade(true)         // animación suave
                .build(),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_menu_report_image)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = artist,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 18.sp
        )
        Text(
            text = year,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadOnlyField(
    value: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        enabled = false, // ✅ hace que use disabledColors
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,      // texto adaptado
            disabledBorderColor = MaterialTheme.colorScheme.outline,      // borde adaptado
            disabledContainerColor = MaterialTheme.colorScheme.surface,   // fondo adaptado al tema
            cursorColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

// ✅ Fila de puntaje adaptada
@Composable
fun ScoreRow(
    scoreLabel: String,
    usersHint: String,
    scoreValue: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = scoreLabel,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Text(
                text = usersHint,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
            )
        }
        ReadOnlyField(
            value = scoreValue,
            modifier = Modifier
                .height(50.dp)
                .padding(end = 150.dp, start = 20.dp)
        )
    }
}

// ✅ Sección con título y subtítulo
@Composable
fun SectionTitle(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
        if (subtitle != null) {
            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, top = 10.dp)
            )
        }
    }
}

// ✅ Fila con dos botones redondeados adaptados al tema
@Composable
fun ActionButtonsRow(
    leftText: String,
    rightText: String,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    leftColor: Color,
    rightColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Button(
            onClick = onLeftClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = leftColor,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier.weight(1f)
        ) {
            Text(leftText)
        }
        Button(
            onClick = onRightClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = rightColor,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier.weight(1f)
        ) {
            Text(rightText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumScreenPreview() {
    Proyecto_movilTheme(useDarkTheme = true) {
        SectionTitle("Tus reseñas", "Subtítulo")
    }
}
