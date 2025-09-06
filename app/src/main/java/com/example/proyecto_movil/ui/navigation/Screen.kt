package com.example.proyecto_movil.ui.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")

    object Profile : Screen("profile/{userId}") {
        fun createRoute(userId: Int) = "profile/$userId"
    }

    object Album : Screen("album/{albumId}") {
        fun createRoute(albumId: Int) = "album/$albumId"
    }

    // Contenido por artista (no owner)
    object ContentArtist : Screen("content_artist/{artistId}") {
        fun createRoute(artistId: Int) = "content_artist/$artistId"
    }

    // Contenido del usuario (owner)
    object ContentUser : Screen("content_user")

    object Settings : Screen("settings")
    object EditProfile : Screen("edit_profile")
    object AddReview : Screen("add_review")
}