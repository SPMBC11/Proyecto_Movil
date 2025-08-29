package com.example.proyecto_movil.navigation

<<<<<<< HEAD
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
=======
>>>>>>> 1f44767dfb8400347110e2d8ad674afb386cd8c7
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD
=======
import androidx.navigation.navArgument

>>>>>>> 1f44767dfb8400347110e2d8ad674afb386cd8c7
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

<<<<<<< HEAD
        // ---------- AUTH ----------
=======
        // ---------- WELCOME ----------
>>>>>>> 1f44767dfb8400347110e2d8ad674afb386cd8c7
        composable(Screen.Welcome.route) {
            WelcomeScreen(onStartClick = { navController.navigate(Screen.SignIn.route) })
        }

        // ---------- SIGN IN ----------
        composable(Screen.SignIn.route) {
            SignInScreen(onLoginClick = { navController.navigate(Screen.Login.route) })
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
                onForgotPassword = { },
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
<<<<<<< HEAD
                onRegister = { _, _, _ -> navController.navigate(Screen.Home.route) },
=======
                onRegister = { _, _, _ ->
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
>>>>>>> 1f44767dfb8400347110e2d8ad674afb386cd8c7
                onLogin = { navController.navigate(Screen.Login.route) }
            )
        }

        // ---------- HOME ----------
        composable(Screen.Home.route) {
            HomeScreen(
<<<<<<< HEAD
                onAlbumClick = { albumUi: AlbumUi ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("album", albumUi)
                    navController.navigate(Screen.Album.route)
=======
                onAlbumClick = { album: AlbumUi ->
                    navController.navigate("${Screen.Album.route}/${album.id}")
>>>>>>> 1f44767dfb8400347110e2d8ad674afb386cd8c7
                },
                onHomeClick = { /* ya estás en Home */ },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        // ---------- PROFILE ----------
        composable(Screen.Profile.route) {
<<<<<<< HEAD
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
=======
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
>>>>>>> 1f44767dfb8400347110e2d8ad674afb386cd8c7
            Artistpage(
                onBack = { navController.navigateUp() },
                onOpenAlbum = { /* si más tarde usas id, vuelves a activar la ruta con id */ },
                onSeeAll = { }
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
<<<<<<< HEAD

@Composable
private fun SimpleErrorScreen(message: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(message, color = Color.White)
    }
}
=======
>>>>>>> 1f44767dfb8400347110e2d8ad674afb386cd8c7
