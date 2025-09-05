package com.example.proyecto_movil.navigation

import com.example.proyecto_movil.screen.mainScreens.AlbumReviewScreen
import WelcomeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.ReviewInfo
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ReviewRepository
import com.example.proyecto_movil.data.local.UserRepository
import com.example.proyecto_movil.screen.components.ArtistPage
import com.example.proyecto_movil.screen.components.ContentUser
import com.example.proyecto_movil.screen.components.EditarPerfil
import com.example.proyecto_movil.screen.components.SettingsScreen
import com.example.proyecto_movil.screen.components.UserReviewScreen
import com.example.proyecto_movil.screen.mainScreens.LoginScreen
import com.example.proyecto_movil.screen.mainScreens.RegisterScreen2
import com.example.proyecto_movil.screen.mainScreens.ReviewDetailScreen
import com.example.proyecto_movil.screen.mainScreens.UserProfileScreen
import com.example.proyecto_movil.uiViews.homePage.HomeScreen
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme

@Composable
fun AppNavHost(
    navController: NavHostController,
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
            WelcomeScreen(onStartClick = { navController.navigate(Screen.Login.route) })
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
                    navController.navigate(Screen.Album.createRoute(albumUi.id))
                },
                onHomeClick = { /* ya estás en Home */ },
                onProfileClick = { navController.navigate(Screen.Profile.createRoute(6)) }
            )
        }

// ---------- PERFIL (USUARIO ACTUAL Y OTROS) ----------
        composable(
            route = Screen.Profile.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            val user = userId?.let { UserRepository.getUserById(it) }

            if (user != null) {
                UserProfileScreen(
                    user = user,
                    reviews = ReviewRepository.getReviewsByUser(user.id),
                    onBackClick = { navController.navigateUp() },
                    onAlbumClick = { album -> navController.navigate("${Screen.Album.route}/${album.id}") },
                    onReviewClick = { review ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("review", review)
                        navController.navigate(Screen.UserReview.route)
                    },
                    onSettingsClick = { navController.navigate(Screen.Settings.route) },
                    onEditProfile = { navController.navigate(Screen.EditProfile.route) }
                )
            } else {
                SimpleErrorScreen("Usuario no encontrado")
            }
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
        composable(
            route = "${Screen.Album.route}/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getInt("albumId")
            val album = AlbumRepository.albums.find { it.id == albumId }
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
                onOpenAlbum = { /* opcional */ },
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
            val userId = navController.currentBackStackEntry?.arguments?.getInt("userId")
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppNavHostPreview() {
    val navController = rememberNavController()
    Proyecto_movilTheme {
        Surface {
            AppNavHost(navController = navController)
        }
    }
}
