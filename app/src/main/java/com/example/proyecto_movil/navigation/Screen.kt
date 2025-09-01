package com.example.proyecto_movil.navigation

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object SignIn : Screen("sign_in")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object Album : Screen("album")
    data object Artist : Screen("artist_page")
    data object AddReview : Screen("add_review")

    data object Settings : Screen("settings")
    data object EditProfile : Screen("EditarPerfil")

    data object ContentUser : Screen("content_user")

    data object Review : Screen("review")

    data object UserReview : Screen("userReview")

}
