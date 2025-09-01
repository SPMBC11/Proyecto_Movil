import android.os.Parcelable
import com.example.proyecto_movil.data.AlbumUI
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaylistUI(
    val id: Int,
    val title: String,
    val description: String = "",
    val albums: List<AlbumUI> = emptyList()
) : Parcelable

