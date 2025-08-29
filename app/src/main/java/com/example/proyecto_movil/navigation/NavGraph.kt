package com.example.proyecto_movil.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_movil.screen.*
import com.example.proyecto_movil.utils.recursos.AlbumUi

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Welcome.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

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
                onForgotPassword = { },
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
                onRegister = { _, _, _ -> navController.navigate(Screen.Home.route) },
                onLogin = { navController.navigate(Screen.Login.route) }
            )
        }

        // ---------- HOME ----------
        composable(Screen.Home.route) {
            HomeScreen(
                onAlbumClick = { albumUi: AlbumUi ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("album", albumUi)
                    navController.navigate(Screen.Album.route)
                },
                onHomeClick = { /* ya estás en Home */ },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        // ---------- PROFILE ----------
        composable(Screen.Profile.route) {
            UserProfileScreen(isCurrentUserProfile = true)
        }

        // ---------- ALBUM (SIN ID) ----------
        composable(Screen.Album.route) { backStackEntry ->
            val album =
                navController.previousBackStackEntry?.savedStateHandle?.get<AlbumUi>("album")
                    ?: backStackEntry.savedStateHandle.get<AlbumUi>("album")

            if (album != null) {
                albumReviewScreen(
                    album = album,
                    onArtistClick = { navController.navigate(Screen.Artist.route) }
                )
            } else {
                SimpleErrorScreen("No hay álbum seleccionado")
            }
        }

        // ---------- ARTISTA (GENÉRICO) ----------
        composable(Screen.Artist.route) {
            Artistpage(
                onBack = { navController.navigateUp() },
                onOpenAlbum = { /* si más tarde usas id, vuelves a activar la ruta con id */ },
                onSeeAll = { }
            )
        }
    }
}

@Composable
private fun SimpleErrorScreen(message: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(message, color = Color.White)
    }
}
