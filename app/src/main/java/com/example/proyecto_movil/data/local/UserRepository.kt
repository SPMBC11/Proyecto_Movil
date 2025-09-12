package com.example.proyecto_movil.data.local

import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.*

object UserRepository {
    val users = listOf(
        UserUI(
            id = 1,
            username = "Juan",
            profilePic = R.drawable.avatar_demo,
            bio = "Amante del hip hop y coleccionista de vinilos 🎶",
            followers = 200,
            following = 150,
            playlists = listOf(PlaylistRepository.playlists[0])
        ),
        UserUI(
            id = 2,
            username = "Laura",
            profilePic = R.drawable.avatar_demo,
            bio = "Fan del pop internacional 🌍",
            followers = 340,
            following = 210,
            playlists = listOf(PlaylistRepository.playlists[1])
        ),
        UserUI(
            id = 3,
            username = "Carlos",
            profilePic = R.drawable.avatar_demo,
            bio = "Explorador de géneros musicales 🎧",
            followers = 120,
            following = 98,
            playlists = listOf(PlaylistRepository.playlists[2])
        ),
        UserUI(
            id = 4,
            username = "Sofía",
            profilePic = R.drawable.avatar_demo,
            bio = "La reina de las fiestas 🔥 EDM y reggaetón",
            followers = 500,
            following = 300,
            playlists = listOf(PlaylistRepository.playlists[3])
        ),
        UserUI(
            id = 5,
            username = "Mateo",
            profilePic = R.drawable.avatar_demo,
            bio = "Cinéfilo y amante de la música experimental 🎬",
            followers = 220,
            following = 180,
            playlists = listOf(PlaylistRepository.playlists[4])
        ),
        UserUI(
            id = 6,
            username = "el.xokas",
            profilePic = R.drawable.xocas,
            bio = "Streamer y crítico musical 🎶",
            followers = 17,
            following = 78,
            playlists = listOf(PlaylistRepository.playlists[4])
        )
    )

    // Usuario autenticado simulado
    private var currentUser: UserUI = users.first { it.username == "el.xokas" }

    // ========= API pública con Result =========

    fun getUsers(): Result<List<UserUI>> = try {
        Result.success(users)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getUserById(id: Int): Result<UserUI> = try {
        val u = users.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Usuario con id $id no encontrado")
        Result.success(u)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getCurrentUser(): Result<UserUI> = try {
        Result.success(currentUser)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun setCurrentUserById(id: Int): Result<UserUI> = try {
        val u = users.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Usuario con id $id no encontrado")
        currentUser = u
        Result.success(u)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
