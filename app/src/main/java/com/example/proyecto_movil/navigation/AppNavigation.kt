package com.example.proyecto_movil.navigation

import AlbumReviewScreen
import WelcomeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_movil.R
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ReviewRepository
import com.example.proyecto_movil.screen.*
import com.example.proyecto_movil.screen.UserReviewScreen

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
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen2(
                modifier = Modifier,
                onBack = { navController.navigateUp() },
                onRegister = { _, _, _ -> navController.navigate(Screen.Home.route) },
                onLogin = { navController.navigate(Screen.Login.route) }
            )
        }

        // ---------- HOME ----------
        composable(Screen.Home.route) {
            HomeScreen(
                onAlbumClick = { albumUi: AlbumUI ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("album", albumUi)
                    navController.navigate(Screen.Album.route)
                },
                onHomeClick = { /* ya estás en Home */ },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        // ---------- PROFILE ----------
        composable(Screen.Profile.route) {
            UserProfileScreen(
                username = "Xokas",
                bio = "Streamer, gamer y crítico musical 🎮🎶",
                followers = 1200,
                following = 500,
                profilePicRes = R.drawable.xocas,
                favoriteAlbums = AlbumRepository.albums.take(3),
                reviews = ReviewRepository.reviews.take(2),
                onAlbumClick = { album ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("album", album)
                    navController.navigate(Screen.Album.route)
                },
                onReviewClick = { review ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("review", review)
                    navController.navigate(Screen.UserReview.route)
                },
                onSettingsClick = { navController.navigate(Screen.Settings.route) },
                onEditProfile = { navController.navigate(Screen.EditProfile.route) }
            )
        }

        // ---------- REVIEW DETAIL ----------
        composable(Screen.Review.route) { backStackEntry ->
            val review = backStackEntry.savedStateHandle.get<ReviewInfo>("review")
            if (review != null) {
                ReviewDetailScreen(
                    review = review,
                    onBack = { navController.navigateUp() }
                )
            } else {
                SimpleErrorScreen("No hay reseña seleccionada")
            }
        }

        // ---------- ALBUM ----------
        composable(Screen.Album.route) { backStackEntry ->
            val album = backStackEntry.savedStateHandle.get<AlbumUI>("album")
            if (album != null) {
                AlbumReviewScreen(
                    album = album,
                    onArtistClick = { navController.navigate(Screen.Artist.route) }
                )
            } else {
                SimpleErrorScreen("No hay álbum seleccionado")
            }
        }

        // ---------- ARTISTA ----------
        composable(Screen.Artist.route) {
            ArtistPage(
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
                onBack = { navController.navigateUp() }
            )
        }

        // ---------- CONTENT USER ----------
        composable(Screen.ContentUser.route) {
            ContentUser(
                onBack = { navController.navigateUp() },
                onOpenAlbum = { navController.navigate(Screen.Album.route) }
            )
        }

        // ---------- USER REVIEW ----------
        composable(Screen.UserReview.route) { backStackEntry ->
            val review = backStackEntry.savedStateHandle.get<ReviewInfo>("review")
            if (review != null) {
                UserReviewScreen(
                    review = review,
                    onBack = { navController.navigateUp() }
                )
            } else {
                SimpleErrorScreen("No hay reseña seleccionada")
            }
        }
    }
}

@Composable
private fun SimpleErrorScreen(message: String) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.onBackground)
    }
}
