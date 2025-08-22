package com.example.proyecto_movil.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.proyecto_movil.screen.*
import com.example.proyecto_movil.screen.ArtistPage
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Welcome.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onStartClick = { navController.navigate(Screen.SignIn.route) }
            )
        }

        composable(Screen.SignIn.route) {
            SignInScreen(
                onLoginClick = { navController.navigate(Screen.Login.route) }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onBack = { navController.navigateUp() },
                onLogin = { _, _, _ -> navController.navigate(Screen.Home.route) },
                onForgotPassword = { /* TODO: navigate to forgot screen if added */ },
                onRegister = { navController.navigate(Screen.Register.route) },
                onGoogle = { navController.navigate(Screen.Home.route) },
                onFacebook = { navController.navigate(Screen.Home.route) },
                onApple = { navController.navigate(Screen.Home.route) },
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen2(
                modifier = Modifier,
                onBack = { navController.navigateUp() },
                onRegister = { email, password, confirmPassword ->
                    // TODO: Handle registration logic here
                    navController.navigate(Screen.Home.route)
                },
                onLogin = { navController.navigate(Screen.Login.route) }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onAlbumClick = { _ -> navController.navigate(Screen.Album.route) },
                onHomeClick = { /* ya estás en Home */ },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.Profile.route) {
            UserProfileScreen()
        }

        composable(Screen.Album.route) {
            albumReviewScreen(
                modifier = Modifier
            )
        }

        composable(Screen.AddReview.route) {
            AddReviewScreen(
                onPublicarClick = { navController.navigateUp() },
                onCancelarClick = { navController.navigateUp() }
            )
        }
        composable(Screen.Artist.route) {
            ArtistPage(
                onBack = { navController.navigateUp() },
                onOpenAlbum = { albumId ->
                    // si usas argumentos:
                    // navController.navigate("${Screen.Album.route}/$albumId")
                    navController.navigate(Screen.Album.route)
                },
                onSeeAll = {
                    // navega a una pantalla “Discography” si la tienes
                    // navController.navigate(Screen.Discography.route)
                }
            )
        }

    }
}