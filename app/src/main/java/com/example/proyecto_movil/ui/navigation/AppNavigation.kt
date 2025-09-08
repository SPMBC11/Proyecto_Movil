package com.example.proyecto_movil.ui.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.local.AlbumRepository
import com.example.proyecto_movil.data.local.ReviewRepository
import com.example.proyecto_movil.data.local.UserRepository
import com.example.proyecto_movil.ui.Screens.Welcome.WelcomeScreen
import com.example.proyecto_movil.ui.Screens.Welcome.WelcomeViewModel
import com.example.proyecto_movil.ui.Screens.Login.LoginScreen
import com.example.proyecto_movil.ui.Screens.Login.LoginViewModel
import com.example.proyecto_movil.ui.Screens.Register.RegisterScreen
import com.example.proyecto_movil.ui.Screens.Register.RegisterViewModel
import com.example.proyecto_movil.ui.Screens.Home.HomeScreen
import com.example.proyecto_movil.ui.Screens.UserProfile.UserProfileScreen
import com.example.proyecto_movil.ui.Screens.UserProfile.UserProfileViewModel
import com.example.proyecto_movil.ui.Screens.Settings.SettingsScreen
import com.example.proyecto_movil.ui.Screens.Settings.SettingsViewModel
import com.example.proyecto_movil.ui.Screens.Content.ContentScreen
import com.example.proyecto_movil.ui.Screens.Content.ContentViewModel
import com.example.proyecto_movil.ui.Screens.AddReview.AddReviewScreen
import com.example.proyecto_movil.ui.Screens.AddReview.AddReviewViewModel
import com.example.proyecto_movil.ui.Screens.EditProfile.EditarPerfilScreen
import com.example.proyecto_movil.ui.Screens.EditProfile.EditProfileViewModel
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.Screens.AlbumReviews.AlbumReviewScreen

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
        /* WELCOME */
        composable(Screen.Welcome.route) {
            val vm: WelcomeViewModel = viewModel()
            WelcomeScreen(
                viewModel = vm,
                onStartClick = { navController.navigate(Screen.Login.route) }
            )
        }

        /* LOGIN */
        composable(Screen.Login.route) {
            val vm: LoginViewModel = viewModel()
            LoginScreen(
                viewModel = vm,
                onBack = { navController.navigateUp() },
                onLogin = { _, _, _ ->
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onForgotPassword = { /* TODO */ },
                onRegister = { navController.navigate(Screen.Register.route) }
            )
        }

        /* REGISTER */
        composable(Screen.Register.route) {
            val vm: RegisterViewModel = viewModel()
            RegisterScreen(
                viewModel = vm,
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

        /* HOME */
        composable(Screen.Home.route) {
            HomeScreen(
                onAlbumClick = { album: AlbumUI ->
                    navController.navigate(Screen.Album.createRoute(album.id))
                },
                onHomeClick = { /* ya estás en Home */ },
                onProfileClick = { navController.navigate(Screen.Profile.createRoute(6)) }
            )
        }

        /* PROFILE */
        composable(
            route = Screen.Profile.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            val user = userId?.let { UserRepository.getUserById(it) }
            if (user != null) {
                val reviews = ReviewRepository.getReviewsByUser(user.id)
                val vm: UserProfileViewModel = viewModel()
                UserProfileScreen(
                    viewModel = vm,
                    user = user,
                    reviews = reviews,
                    onBackClick = { navController.navigateUp() },
                    onAlbumClick = { album -> navController.navigate(Screen.Album.createRoute(album.id)) },
                    onReviewClick = { /* navegar a detalle si lo tienes */ },
                    onSettingsClick = { navController.navigate(Screen.Settings.route) },
                    onEditProfile = { navController.navigate(Screen.EditProfile.route) }
                )
            } else {
                SimpleError("Usuario no encontrado")
            }
        }

        /* ALBUM (reseñas del álbum) */
        composable(
            route = Screen.Album.route,
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getInt("albumId")
            val album = AlbumRepository.albums.find { it.id == albumId }
            if (album != null) {
                AlbumReviewScreen(
                    album = album,
                    onArtistClick = {
                        navController.navigate(Screen.ContentArtist.createRoute(album.artist.id))
                    },
                    onUserClick = { userId ->
                        navController.navigate(Screen.Profile.createRoute(userId))
                    }
                )
            } else {
                SimpleError("Álbum no encontrado")
            }
        }

        /* CONTENT por artista (NO owner) */
        composable(
            route = Screen.ContentArtist.route,
            arguments = listOf(navArgument("artistId") { type = NavType.IntType })
        ) { backStackEntry ->
            val artistId = backStackEntry.arguments?.getInt("artistId")
            val vm: ContentViewModel = viewModel()
            LaunchedEffect(artistId) { vm.setInitial(artistId = artistId, isOwner = false) }
            ContentScreen(
                viewModel = vm,
                onBack = { navController.navigateUp() },
                onOpenAlbum = { id -> navController.navigate(Screen.Album.createRoute(id)) },
                onSeeAll = { /* navegar a discografía completa si quieres */ }
            )
        }

        /* CONTENT del usuario (owner) */
        composable(Screen.ContentUser.route) {
            val vm: ContentViewModel = viewModel()
            LaunchedEffect(Unit) { vm.setInitial(artistId = null, isOwner = true) }
            ContentScreen(
                viewModel = vm,
                onBack = { navController.navigateUp() },
                onOpenAlbum = { id -> navController.navigate(Screen.Album.createRoute(id)) },
                onEditAlbum = { /* abrir editor de lista/álbum si lo implementas */ }
            )
        }

        /* SETTINGS */
        composable(Screen.Settings.route) {
            val vm: SettingsViewModel = viewModel()
            SettingsScreen(
                viewModel = vm,
                onBackClick = { navController.navigateUp() }
            )
        }

        /* EDIT PROFILE */
        composable(Screen.EditProfile.route) {
            val vm: EditProfileViewModel = viewModel()
            EditarPerfilScreen(
                viewModel = vm,
                onBack = { navController.navigateUp() },
                onSaved = { navController.navigateUp() }
            )
        }

        /* ADD REVIEW (opcional si la usas) */
        composable(Screen.AddReview.route) {
            val vm: AddReviewViewModel = viewModel()
            AddReviewScreen(
                viewModel = vm,
                onCancelarClick = { navController.navigateUp() },
                onPublicarClick = { navController.navigateUp() }
            )
        }
    }
}

/* ---------------------- Utils de error + preview ---------------------- */
@Composable
private fun SimpleError(message: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
