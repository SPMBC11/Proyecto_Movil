package com.example.proyecto_movil.data.local

import com.example.proyecto_movil.data.ReviewInfo

object ReviewRepository {
    val reviews = listOf(
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "CIRCLES" },
            user = UserRepository.users[0], // Juan
            content = "Un álbum íntimo y lleno de emociones. Lo escucho una y otra vez.",
            score = 9.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "emails i can't send" },
            user = UserRepository.users[1], // Laura
            content = "Excelente producción pop, aunque algunas canciones son muy comerciales.",
            score = 7.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "FERXXO VOL 10: SAGRADO" },
            user = UserRepository.users[2], // Carlos
            content = "Demasiado repetitivo, esperaba más innovación en el sonido.",
            score = 5.5,
            isLowScore = true
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Party Rock" },
            user = UserRepository.users[3], // Sofía
            content = "Party Rock es puro fuego 🔥, nunca falta en mis fiestas.",
            score = 8.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Mulholland Drive OST" },
            user = UserRepository.users[4], // Mateo
            content = "El OST de Mulholland Drive es inquietante y maravilloso. Una obra de arte.",
            score = 9.5,
            isLowScore = false
        )
    )

    fun getReviewsByUser(userId: Int) =
        reviews.filter { it.user.id == userId }
}
