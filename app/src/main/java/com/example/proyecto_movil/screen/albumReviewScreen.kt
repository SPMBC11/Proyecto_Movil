import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ReviewRepository
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.utils.AlbumHeader
import com.example.proyecto_movil.utils.ClickableSectionTitle
import com.example.proyecto_movil.utils.ScoreRow
import com.example.proyecto_movil.utils.ScreenBackground
import com.example.proyecto_movil.utils.SettingsIcon
import com.example.proyecto_movil.utils.TitleBar
import com.example.proyecto_movil.utils.UserReview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumReviewScreen(
    album: AlbumUI,
    modifier: Modifier = Modifier,
    onArtistClick: () -> Unit = {}
) {
    val albumReviews = ReviewRepository.reviews.filter { it.album.id == album.id }

    // 🔹 Fondo dinámico según tema
    val backgroundRes = if (isSystemInDarkTheme()) {
        R.drawable.fondocriti
    } else {
        R.drawable.fondocriti_light
    }

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        SettingsIcon(modifier = Modifier.align(Alignment.TopEnd))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 55.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleBar(text = stringResource(id = R.string.titulo_resenas))

            Spacer(Modifier.height(32.dp))

            AlbumHeader(
                coverRes = album.coverRes,
                title = album.title,
                artist = album.artist.name,
                year = album.year
            )

            Spacer(Modifier.height(16.dp))

            // 🔹 Imagen del artista
            Image(
                painter = painterResource(id = album.artist.profilePic),
                contentDescription = "Foto de ${album.artist.name}",
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .clickable { onArtistClick() },
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(20.dp))

            // 🔹 Puntaje general del álbum
            ScoreRow(
                scoreLabel = stringResource(id = R.string.puntaje_album),
                usersHint = stringResource(id = R.string.cantidad_usuarios_alb),
                scoreValue = if (albumReviews.isNotEmpty())
                    (albumReviews.map { it.score }.average() * 10).toInt().toString() + "%"
                else
                    "N/A"
            )

            Spacer(Modifier.height(16.dp))

            ClickableSectionTitle(
                title = stringResource(id = R.string.resenas_album),
                onSeeAll = { /* Navegar a todas las reseñas */ }
            )

            Spacer(Modifier.height(20.dp))

            Column {
                if (albumReviews.isEmpty()) {
                    Text(
                        text = "Aún no hay reseñas para este álbum",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                } else {
                    albumReviews.forEach { review ->
                        UserReview(
                            userImage = review.user.profilePic,
                            userName = review.user.username,
                            reviewText = review.content,
                            isLiked = review.score > 6
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showSystemUi = true)
@Composable
fun AlbumReviewScreenPreviewLight() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AlbumReviewScreen(
                album = AlbumRepository.albums.first()
            )
        }
    }
}

@Preview(name = "Dark Mode", showSystemUi = true)
@Composable
fun AlbumReviewScreenPreviewDark() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AlbumReviewScreen(
                album = AlbumRepository.albums.first()
            )
        }
    }
}
