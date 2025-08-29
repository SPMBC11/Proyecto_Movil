package com.example.proyecto_movil.navigation

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object SignIn : Screen("sign_in")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object Album : Screen("album")
    data object Artist : Screen("artist")
    data object AddReview : Screen("add_review")

    // ya las tienes:
    data object Settings : Screen("settings")
    data object EditProfile : Screen("EditarPerfil")

    // ➕ NUEVA:
    data object ContentUser : Screen("content_user")
}
