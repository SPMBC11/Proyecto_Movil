package com.example.proyecto_movil.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.proyecto_movil.feature.album.ui.Artistpage
import com.example.proyecto_movil.screen.*
import com.example.proyecto_movil.utils.recursos.AlbumUi

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Welcome.route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        // ---------- AUTH ----------
        composable(Screen.Welcome.route) {
            WelcomeScreen(onStartClick = { navController.navigate(Screen.SignIn.route) })
        }

        composable(Screen.SignIn.route) {
            SignInScreen(onLoginClick = { navController.navigate(Screen.Login.route) })
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onBack = { navController.navigateUp() },
                onLogin = { _, _, _ ->
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onForgotPassword = { /* TODO */ },
                onRegister = { navController.navigate(Screen.Register.route) },
                onGoogle = { navController.navigate(Screen.Home.route) },
                onFacebook = { navController.navigate(Screen.Home.route) },
                onApple = { navController.navigate(Screen.Home.route) },
            )
        }

        composable(Screen.Register.route) {
            RegisterRoute(
                onBack = { navController.navigateUp() },
                onRegistered = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onLogin = { navController.navigate(Screen.Login.route) }
            )
        }

        // ---------- HOME ----------
        composable(Screen.Home.route) {
            HomeScreen(
                onAlbumClick = { albumUi: AlbumUi ->
                    navController.navigate("${Screen.Album.route}/${albumUi.id}")
                },
                onHomeClick = { /* ya estás en Home */ },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        // ---------- PROFILE ----------
        composable(Screen.Profile.route) {
            UserProfileScreen(
                isCurrentUserProfile = true,
                onEditProfile = { navController.navigate(Screen.EditProfile.route) },
                onSettings    = { navController.navigate(Screen.Settings.route) },
                onOpenContent = { navController.navigate(Screen.ContentUser.route) },
                onBack        = { navController.navigateUp() }
            )
        }

        // ---------- ALBUM (CON ID; usa SavedStateHandle) ----------
        composable(
            route = "${Screen.Album.route}/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { backStackEntry ->
            AlbumReviewRoute(
                backStackEntry = backStackEntry,
                onArtistClick = { navController.navigate(Screen.Artist.route) }
            )
        }

        // ---------- ARTISTA (GENÉRICO) ----------
        composable(Screen.Artist.route) {
            Artistpage(
                onBack = { navController.navigateUp() },
                onOpenAlbum = { /* si usas id, navega a Album con id */ },
                onSeeAll = { /* opcional */ }
            )
        }

        // ---------- SETTINGS ----------
        composable(Screen.Settings.route) {
            SettingsScreen(
                onBackClick = { navController.navigateUp() }
            )
        }

        // ---------- EDITAR PERFIL ----------
        composable(Screen.EditProfile.route) {
            EditarPerfil(
                onBackClick = { navController.navigateUp() },
                onBack      = { navController.navigateUp() }
            )
        }

        // ---------- CONTENT USER ----------
        composable(Screen.ContentUser.route) {
            ContentUser(
                onBack = { navController.navigateUp() },
                onOpenAlbum = { albumId ->
                    navController.navigate("${Screen.Album.route}/$albumId")
                }
            )
        }
    }
}

@Composable
private fun SimpleErrorScreen(message: String) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        androidx.compose.material3.Text(text = message, color = androidx.compose.ui.graphics.Color.White)
    }
}
