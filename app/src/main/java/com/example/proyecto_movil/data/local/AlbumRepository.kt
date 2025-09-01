package com.example.proyecto_movil.data.local

import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI

object AlbumRepository {
    val albums = listOf(
        // Sabrina Carpenter
        AlbumUI(102, "SHORT N`SWEET", "2024", R.drawable.shortnsweet, ArtistRepository.artists[0]),
        AlbumUI(103, "emails i can't send", "2023", R.drawable.emailsicantsend, ArtistRepository.artists[0]),

        // Mac Miller
        AlbumUI(201, "CIRCLES", "2020", R.drawable.circles, ArtistRepository.artists[1]),
        // Reemplazado Best of Mac → David Lynch
        AlbumUI(202, "Mulholland Drive OST", "2001", R.drawable.david_lynch_pfp, ArtistRepository.artists[19]),

        // Aminé
        AlbumUI(301, "Good for you", "2022", R.drawable.goodforyou, ArtistRepository.artists[2]),

        // Drake
        AlbumUI(401, "Views", "2016", R.drawable.views, ArtistRepository.artists[3]),

        // Feid
        AlbumUI(501, "FERXXO VOL 10: SAGRADO", "2024", R.drawable.ferxxo_vol_10_sagrado, ArtistRepository.artists[4]),

        // Tyler
        AlbumUI(601, "DON'T TAP THE GLASS", "2024", R.drawable.tyler_dttg, ArtistRepository.artists[5]),

        // Billie Eilish
        AlbumUI(701, "HIT ME HARD AND SOFT", "2024", R.drawable.hmhns, ArtistRepository.artists[6]),

        // Bad Bunny
        AlbumUI(801, "DbTmF", "2024", R.drawable.dbtmf, ArtistRepository.artists[7]),

        // Andrés Cepeda
        AlbumUI(901, "BOGOTÁ (DELUXE)", "2023", R.drawable.bogota_deluxe, ArtistRepository.artists[8]),

        // AKRIILA
        AlbumUI(1001, "EPISTOLARES+", "2024", R.drawable.epistolares, ArtistRepository.artists[9]),

        // Danny Ocean
        AlbumUI(1101, "BABYLON CLUB", "2024", R.drawable.babylon, ArtistRepository.artists[10]),

        // Gipsy Kings
        AlbumUI(1201, "GIPSY KINGS", "1988", R.drawable.gypsy_kings, ArtistRepository.artists[11]),

        // Abraham Mateo
        AlbumUI(1301, "ABRAHAM MATEO", "2014", R.drawable.abraham1, ArtistRepository.artists[12]),
        AlbumUI(1302, "LÁNZALO", "2015", R.drawable.abraham2, ArtistRepository.artists[12]),

        // Kendrick Lamar
        AlbumUI(1401, "GNX", "2024", R.drawable.gnx, ArtistRepository.artists[13]),

        // Shakira
        AlbumUI(1501, "FIJACIÓN ORAL VOL. 1", "2005", R.drawable.fijacion_oral, ArtistRepository.artists[14]),

        // Binomio de Oro
        AlbumUI(1601, "2000", "2000", R.drawable.dosmil, ArtistRepository.artists[15]),

        // Mr. Black
        AlbumUI(1701, "Presidente de la Champeta", "2010", R.drawable.mr_black_pfp, ArtistRepository.artists[16]),

        // LMFAO
        AlbumUI(1801, "Party Rock", "2011", R.drawable.lmfao_pfp, ArtistRepository.artists[17]),

        // Justin Bieber
        AlbumUI(1901, "Swag", "2012", R.drawable.justin_bieber_pfp, ArtistRepository.artists[18])
    )
}
