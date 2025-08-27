package com.example.proyecto_movil.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument // ✅ IMPORT CORRECTO

import com.example.proyecto_movil.data.local.Catalog // ✅ si usas Catalog
import com.example.proyecto_movil.screen.* // ✅ trae WelcomeScreen, LoginScreen, Artistpage, etc.

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Welcome.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        // ---------- FLUJO AUTH ----------
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
                onLogin = { _, _, _ ->
                    navController.navigate(Screen.Home.route) {
                        // Limpia el backstack de auth si quieres:
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
            RegisterScreen2(
                modifier = Modifier,
                onBack = { navController.navigateUp() },
                onRegister = { _, _, _ ->
                    // TODO lógica registro
                    navController.navigate(Screen.Home.route)
                },
                onLogin = { navController.navigate(Screen.Login.route) }
            )
        }

        // ---------- HOME / PROFILE ----------
        composable(Screen.Home.route) {
            HomeScreen(
                onAlbumClick = { _ -> navController.navigate(Screen.Album.route) }, // simple (sin id)
                onHomeClick = { /* ya estás en Home */ },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.Profile.route) {
            UserProfileScreen()
        }

        // ---------- ALBUM (SIN ID) ----------
        composable(Screen.Album.route) {
            albumReviewScreen(
                onArtistClick = {
                    // Si quieres abrir la versión genérica de artista (sin id):
                    navController.navigate(Screen.Artist.route)
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

        // ---------- ARTISTA (SIN ID) ----------
        composable(Screen.Artist.route) {
            // Si tu Artistpage NO requiere artistId:
            Artistpage(
                onBack = { navController.navigateUp() },
                onOpenAlbum = { albumId ->
                    navController.navigate("${Screen.Album.route}/$albumId")
                },
                onSeeAll = { /* opcional */ }
            )

            // Si tu Artistpage SÍ requiere artistId, usa esto en su lugar:
            // val defaultId = Catalog.artists.firstOrNull()?.id ?: return@composable
            // Artistpage(
            //     artistId = defaultId,
            //     onBack = { navController.navigateUp() },
            //     onOpenAlbum = { targetAlbumId -> navController.navigate("${Screen.Album.route}/$targetAlbumId") },
            //     onSeeAll = { }
            // )
        }

        // ---------- ALBUM (CON ID) ----------
        composable(
            route = "${Screen.Album.route}/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { entry ->
            val albumId = entry.arguments?.getInt("albumId") ?: return@composable
            val album = Catalog.albumById(albumId)
            if (album == null) {
                navController.navigateUp(); return@composable
            }
            albumReviewScreen(
                onArtistClick = {
                    // Desde un álbum concreto, abre el artista con id:
                    navController.navigate("${Screen.Artist.route}/${album.artistId}")
                }
            )
        }

        // ---------- ARTISTA (CON ID) ----------
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
    }
}
