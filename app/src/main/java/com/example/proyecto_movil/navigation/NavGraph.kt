package com.example.proyecto_movil.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.proyecto_movil.screen.*
import com.example.proyecto_movil.utils.recursos.AlbumUi

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Welcome.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        // ---------- WELCOME ----------
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onStartClick = { navController.navigate(Screen.SignIn.route) }
            )
        }

        // ---------- SIGN IN ----------
        composable(Screen.SignIn.route) {
            SignInScreen(
                onLoginClick = { navController.navigate(Screen.Login.route) }
            )
        }

        // ---------- LOGIN ----------
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
                onGoogle = { /* TODO: auth social */ },
                onFacebook = { /* TODO: auth social */ },
                onApple = { /* TODO: auth social */ }
            )
        }

        // ---------- REGISTER ----------
        composable(Screen.Register.route) {
            RegisterScreen2(
                onBack = { navController.navigateUp() },
                onRegister = { _, _, _ ->
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
                onAlbumClick = { album: AlbumUi ->
                    navController.navigate("${Screen.Album.route}/${album.id}")
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
                onSettings = { navController.navigate(Screen.Settings.route) },
                onOpenContent = { navController.navigate(Screen.ContentUser.route) },
                onBack = { navController.navigateUp() }
            )
        }

        // ---------- ALBUM (con ID) ----------
        composable(
            route = "${Screen.Album.route}/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { backStackEntry ->
            // val albumId = backStackEntry.arguments?.getInt("albumId")
            albumReviewScreen(
                onArtistClick = {
                    // TODO: reemplaza "1" por el artistId real si lo tienes
                    navController.navigate("${Screen.Artist.route}/1")
                }
            )
        }

        // ---------- ADD REVIEW ----------
        composable(Screen.AddReview.route) {
            AddReviewScreen(
                onPublicarClick = { navController.navigateUp() },
                onCancelarClick = { navController.navigateUp() }
            )
        }

        // ---------- ARTISTA (con ID) ----------
        composable(
            route = "${Screen.Artist.route}/{artistId}",
            arguments = listOf(navArgument("artistId") { type = NavType.IntType })
        ) { entry ->
            val artistId = entry.arguments?.getInt("artistId") ?: return@composable
            Artistpage(
                artistId = artistId,
                onBack = { navController.navigateUp() },
                onOpenAlbum = { targetAlbumId ->
                    navController.navigate("${Screen.Album.route}/$targetAlbumId")
                },
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
                onBack = { navController.navigateUp() }
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
