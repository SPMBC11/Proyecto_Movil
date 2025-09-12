package com.example.proyecto_movil.data.local

import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.ReviewInfo

object ReviewRepository {
    val reviews = listOf(
        // 🔹 Usuario 1 (Juan)
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "CIRCLES" },
            user = UserRepository.users[0],
            content = "Un álbum íntimo y lleno de emociones. Lo escucho una y otra vez.",
            score = 9.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "FIJACIÓN ORAL VOL. 1" },
            user = UserRepository.users[0],
            content = "Shakira en su punto más creativo, lleno de clásicos.",
            score = 9.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "BOGOTÁ (DELUXE)" },
            user = UserRepository.users[0],
            content = "Cepeda logra un álbum elegante y muy bien producido.",
            score = 8.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "DON'T TAP THE GLASS" },
            user = UserRepository.users[0],
            content = "Tyler sigue sorprendiendo, producción impecable.",
            score = 9.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "2000" },
            user = UserRepository.users[0],
            content = "Buen vallenato con nostalgia y sentimiento.",
            score = 7.0,
            isLowScore = false
        ),

        // 🔹 Usuario 2 (Laura)
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "emails i can't send" },
            user = UserRepository.users[1],
            content = "Excelente producción pop, aunque algunas canciones son muy comerciales.",
            score = 7.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Views" },
            user = UserRepository.users[1],
            content = "Un clásico de Drake con grandes hits, aunque algo largo.",
            score = 7.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "EPISTOLARES+" },
            user = UserRepository.users[1],
            content = "AKRIILA sorprende con un estilo fresco en el hip hop.",
            score = 7.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "GNX" },
            user = UserRepository.users[1],
            content = "Kendrick innovando como siempre, lírica brutal.",
            score = 9.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "SHORT N`SWEET" },
            user = UserRepository.users[1],
            content = "Un pop fresco, aunque un poco ligero para mi gusto.",
            score = 6.5,
            isLowScore = true
        ),

        // 🔹 Usuario 3 (Carlos)
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "FERXXO VOL 10: SAGRADO" },
            user = UserRepository.users[2],
            content = "Demasiado repetitivo, esperaba más innovación en el sonido.",
            score = 5.5,
            isLowScore = true
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "SHORT N`SWEET" },
            user = UserRepository.users[2],
            content = "Un álbum divertido, ligero y lleno de frescura pop.",
            score = 7.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "DbTmF" },
            user = UserRepository.users[2],
            content = "Bad Bunny arriesgado pero poderoso, aunque no para todos.",
            score = 7.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "ABRAHAM MATEO" },
            user = UserRepository.users[2],
            content = "Un inicio prometedor para Abraham Mateo.",
            score = 6.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Swag" },
            user = UserRepository.users[2],
            content = "Un disco juvenil de Justin, fresco aunque inmaduro.",
            score = 6.5,
            isLowScore = true
        ),

        // 🔹 Usuario 4 (Sofía)
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Party Rock" },
            user = UserRepository.users[3],
            content = "Party Rock es puro fuego 🔥, nunca falta en mis fiestas.",
            score = 8.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Good for you" },
            user = UserRepository.users[3],
            content = "Debut sólido de Aminé, lleno de energía y personalidad.",
            score = 8.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "LÁNZALO" },
            user = UserRepository.users[3],
            content = "Buen pop juvenil, aunque algo genérico.",
            score = 6.0,
            isLowScore = true
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Presidente de la Champeta" },
            user = UserRepository.users[3],
            content = "Mr. Black encendiendo las fiestas con champeta pura.",
            score = 8.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "BABYLON CLUB" },
            user = UserRepository.users[3],
            content = "Danny Ocean trae ritmos pegajosos, muy tropical.",
            score = 8.0,
            isLowScore = false
        ),

        // 🔹 Usuario 5 (Mateo)
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Mulholland Drive OST" },
            user = UserRepository.users[4],
            content = "El OST de Mulholland Drive es inquietante y maravilloso. Una obra de arte.",
            score = 9.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "HIT ME HARD AND SOFT" },
            user = UserRepository.users[4],
            content = "Billie entrega un trabajo íntimo y atmosférico.",
            score = 8.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "GIPSY KINGS" },
            user = UserRepository.users[4],
            content = "Un clásico atemporal, imposible no bailar.",
            score = 9.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "2000" },
            user = UserRepository.users[4],
            content = "Un vallenato de época, nostálgico y alegre.",
            score = 7.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "CIRCLES" },
            user = UserRepository.users[4],
            content = "Mac Miller en estado puro, conmovedor.",
            score = 8.5,
            isLowScore = false
        ),
        // 🔹 Usuario 6 (el.xokas)
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "CIRCLES" },
            user = UserRepository.users[5], // Xokas
            content = "Gran obra de Mac, transmite sentimientos reales. Imprescindible.",
            score = 9.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Views" },
            user = UserRepository.users[5],
            content = "Drake tiene temazos aquí, pero también mucho relleno.",
            score = 6.5,
            isLowScore = true
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "HIT ME HARD AND SOFT" },
            user = UserRepository.users[5],
            content = "Billie Eilish me sorprendió. Muy atmosférico y con carácter.",
            score = 8.5,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "Party Rock" },
            user = UserRepository.users[5],
            content = "Fiesta garantizada, pero nada profundo. Sirve para lo que es.",
            score = 7.0,
            isLowScore = false
        ),
        ReviewInfo(
            album = AlbumRepository.albums.first { it.title == "FIJACIÓN ORAL VOL. 1" },
            user = UserRepository.users[5],
            content = "Clásico de Shakira, pero me sobran algunos temas.",
            score = 8.0,
            isLowScore = false
        )
    )

    // ===== Métodos públicos con Result =====
    fun getReviews(): Result<List<ReviewInfo>> = try {
        Result.success(reviews)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getReviewsByUser(userId: Int): Result<List<ReviewInfo>> = try {
        Result.success(reviews.filter { it.user.id == userId })
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getReviewsByAlbum(albumId: Int): Result<List<ReviewInfo>> = try {
        Result.success(reviews.filter { it.album.id == albumId })
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getFavoriteAlbumsByUser(userId: Int, minScore: Double = 8.0): Result<List<AlbumUI>> = try {
        Result.success(
            reviews
                .filter { it.user.id == userId && it.score >= minScore }
                .map { it.album }
                .distinct()
        )
    } catch (e: Exception) {
        Result.failure(e)
    }

}
