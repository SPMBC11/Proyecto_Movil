package com.example.proyecto_movil.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.ArtistUI
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme

@Composable
fun AlbumCard(
    album: AlbumUI,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = album.coverRes),
            contentDescription = album.title,
            modifier = Modifier
                .size(120.dp), // imagen cuadrada
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = album.title,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,                // 🔑 permitimos hasta 2 líneas
            modifier = Modifier.width(120.dp) // se ajusta al ancho de la imagen
        )

        Text(
            text = album.artist.name,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            maxLines = 1,
            modifier = Modifier.width(120.dp) // también alineado con la imagen
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AlbumCardPreview() {
    val fakeAlbum = AlbumUI(
        id = 1,
        title = "The Dark Side of the Moon",
        year = "1973",
        coverRes = R.drawable.emailsicantsend, // Usa un drawable de tu res
        artist = ArtistUI(
            id = 1,
            name = "Pink Floyd",
            profilePic = R.drawable.sabrina,
            genre = "Rock"
        )
    )

    Proyecto_movilTheme {
        AlbumCard(album = fakeAlbum, onClick = {})
    }
}
