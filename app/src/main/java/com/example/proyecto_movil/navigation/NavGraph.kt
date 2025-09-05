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
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

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
            // Crear ViewModel en NavGraph y pasarlo a la ruta
            val loginVm: com.example.proyecto_movil.feature.auth.ui.LoginViewModel =
                androidx.lifecycle.viewmodel.compose.viewModel(
                    factory = viewModelFactory {
                        initializer {
                            com.example.proyecto_movil.feature.auth.ui.LoginViewModel(
                                com.example.proyecto_movil.core.ServiceLocator.authRepository
                            )
                        }
                    }
                )
            LoginRoute(
                vm = loginVm,
                onBack = { navController.navigateUp() },
                onLoggedIn = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onForgotPassword = { /* TODO */ },
                onRegister = { navController.navigate(Screen.Register.route) },
                onGoogle = { navController.navigate(Screen.Home.route) },
                onFacebook = { navController.navigate(Screen.Home.route) },
                onApple = { navController.navigate(Screen.Home.route) }
            )
        }

        composable(Screen.Register.route) {
            // Crear ViewModel en NavGraph y pasarlo a la ruta
            val registerVm: com.example.proyecto_movil.feature.auth.ui.RegisterViewModel =
                androidx.lifecycle.viewmodel.compose.viewModel()
            RegisterRoute(
                vm = registerVm,
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
            // Crear ViewModel en NavGraph y pasarlo a la ruta
            val profileVm: com.example.proyecto_movil.feature.profile.ui.UserProfileViewModel =
                androidx.lifecycle.viewmodel.compose.viewModel(
                    factory = viewModelFactory {
                        initializer {
                            com.example.proyecto_movil.feature.profile.ui.UserProfileViewModel(
                                com.example.proyecto_movil.core.ServiceLocator.profileRepository
                            )
                        }
                    }
                )
            UserProfileRoute(
                vm = profileVm,
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
            EditProfileRoute(
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
