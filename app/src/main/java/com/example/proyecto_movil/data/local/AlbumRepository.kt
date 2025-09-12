package com.example.proyecto_movil.data.local

import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI

object AlbumRepository {
    val albums = listOf(
        // Sabrina Carpenter
        AlbumUI(102, "SHORT N`SWEET", "2024", "https://i.scdn.co/image/ab67616d00001e02fd8d7a8d96871e791cb1f626", ArtistRepository.artists[0]),
        AlbumUI(103, "emails i can't send", "2023", "https://i.scdn.co/image/ab67616d0000b273700f7bf79c9f063ad0362bdf", ArtistRepository.artists[0]),

        // Mac Miller
        AlbumUI(201, "CIRCLES", "2020", "https://i.scdn.co/image/ab67616d0000b27326b7dd89810cc1a40ada642c", ArtistRepository.artists[1]),
        // Reemplazado Best of Mac → David Lynch
        AlbumUI(202, "Mulholland Drive OST", "2001", "https://i.ytimg.com/vi/mNYF1wWZ_H4/maxresdefault.jpg", ArtistRepository.artists[19]),

        // Aminé
        AlbumUI(301, "Good for you", "2022", "https://www.bing.com/images/search?view=detailV2&ccid=6kKBizM3&id=2BE9413D8F56D401226DDC0DE7056DCB3B63EFB8&thid=OIP.6kKBizM3w6Rr6EAdy_LWqgHaHa&mediaurl=https%3a%2f%2fwww.thebackpackerz.com%2fwp-content%2fuploads%2f2017%2f07%2famine-good-for-you-album-800x800.jpg&cdnurl=https%3a%2f%2fth.bing.com%2fth%2fid%2fR.ea42818b3337c3a46be8401dcbf2d6aa%3frik%3duO9jO8ttBecN3A%26pid%3dImgRaw%26r%3d0&exph=800&expw=800&q=%22Good+for+you%22+amine&FORM=IRPRST&ck=D3444B6A6143706810D4346AFA86F661&selectedIndex=0&itb=0", ArtistRepository.artists[2]),

        // Drake
        AlbumUI(401, "Views", "2016", "https://i.scdn.co/image/ab67616d0000b2739416ed64daf84936d89e671c", ArtistRepository.artists[3]),

        // Feid
        AlbumUI(501, "FERXXO VOL 10: SAGRADO", "2024", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBDgvr24NjEjrlJ-Rsq3lf6NQuYuLRbXE-mg&s", ArtistRepository.artists[4]),

        // Tyler
        AlbumUI(601, "DON'T TAP THE GLASS", "2024", "https://i.scdn.co/image/ab67616d0000b273979047951fa8b15df9f9e984", ArtistRepository.artists[5]),

        // Billie Eilish
        AlbumUI(701, "HIT ME HARD AND SOFT", "2024", "https://i.scdn.co/image/ab67616d0000b27371d62ea7ea8a5be92d3c1f62", ArtistRepository.artists[6]),

        // Bad Bunny
        AlbumUI(801, "DbTmF", "2024", "https://image-cdn-ak.spotifycdn.com/image/ab67706c0000da8408dbfe73b692078d710e54f4", ArtistRepository.artists[7]),

        // Andrés Cepeda
        AlbumUI(901, "BOGOTÁ (DELUXE)", "2023", "https://i.scdn.co/image/ab67616d0000b2735ea3b9dd61366bf82bd1ab96", ArtistRepository.artists[8]),

        // AKRIILA
        AlbumUI(1001, "EPISTOLARES+", "2024", "https://i.scdn.co/image/ab67616d0000b273f3b8bac4ec47a6fb1fa626da", ArtistRepository.artists[9]),

        // Danny Ocean
        AlbumUI(1101, "BABYLON CLUB", "2024", "https://tse4.mm.bing.net/th/id/OIP.4_x5fqCiSA_wClq9RA0zagHaHa?rs=1&pid=ImgDetMain&o=7&rm=3", ArtistRepository.artists[10]),

        // Gipsy Kings
        AlbumUI(1201, "GIPSY KINGS", "1988", "https://i.scdn.co/image/ab67616d0000b273d93675d4a5c0f92e63121415", ArtistRepository.artists[11]),

        // Abraham Mateo
        AlbumUI(1301, "ABRAHAM MATEO", "2014", "https://i.scdn.co/image/ab67616d0000b2739644c304c02e2cc20ab63192", ArtistRepository.artists[12]),
        AlbumUI(1302, "LÁNZALO", "2015", "https://i.scdn.co/image/ab67616d0000b273f641ff7ce301a038b42c873e", ArtistRepository.artists[12]),

        // Kendrick Lamar
        AlbumUI(1401, "GNX", "2024", "https://i.scdn.co/image/ab67616d0000b273d9985092cd88bffd97653b58", ArtistRepository.artists[13]),

        // Shakira
        AlbumUI(1501, "FIJACIÓN ORAL VOL. 1", "2005", "https://i.scdn.co/image/ab67616d0000b273f342e70aacda9d78cfb6ce7a", ArtistRepository.artists[14]),

        // Binomio de Oro
        AlbumUI(1601, "2000", "2000", "https://i.scdn.co/image/ab67616d0000b273d0b8c555dc3c3fe62f8828da", ArtistRepository.artists[15]),

        // Mr. Black
        AlbumUI(1701, "Presidente de la Champeta", "2010", "https://i.scdn.co/image/ab67616d0000b273759c8fefb6bcdc69a711c9e1", ArtistRepository.artists[16]),

        // LMFAO
        AlbumUI(1801, "Party Rock", "2011", "https://i.scdn.co/image/ab67616d0000b2736a0f2bf4749bddc11f4ba8dc", ArtistRepository.artists[17]),

        // Justin Bieber
        AlbumUI(1901, "Swag", "2012", "https://cdn.massa.com.br/uploads/2025/07/20250711131212-justin-bieber-1024x691.jpg", ArtistRepository.artists[18])
    )
}
