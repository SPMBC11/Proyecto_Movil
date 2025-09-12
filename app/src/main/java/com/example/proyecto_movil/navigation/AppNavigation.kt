package com.example.proyecto_movil.navigation

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
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.proyecto_movil.navigation.Screen
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme

// Screens + ViewModels
import com.example.proyecto_movil.ui.Screens.Welcome.WelcomeScreen
import com.example.proyecto_movil.ui.Screens.Welcome.WelcomeViewModel
import com.example.proyecto_movil.ui.Screens.Login.LoginScreen
import com.example.proyecto_movil.ui.Screens.Login.LoginViewModel
import com.example.proyecto_movil.ui.Screens.Register.RegisterScreen
import com.example.proyecto_movil.ui.Screens.Register.RegisterViewModel
import com.example.proyecto_movil.uiViews.homePage.HomeScreen
import com.example.proyecto_movil.ui.Screens.Home.HomeViewModel
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
import com.example.proyecto_movil.ui.Screens.AlbumReviews.AlbumReviewScreen

import com.example.proyecto_movil.data.UserUI
import com.example.proyecto_movil.data.ReviewInfo





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
            val vm: WelcomeViewModel = hiltViewModel()
            WelcomeScreen(
                viewModel = vm,
                onStartClick = { navController.navigate(Screen.Login.route) }
            )
        }

        /* LOGIN */
        composable(Screen.Login.route) {
            val vm: LoginViewModel = hiltViewModel()
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
            val vm: RegisterViewModel = hiltViewModel()
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
        val vm: HomeViewModel = hiltViewModel()
        HomeScreen(
            viewModel = vm,
            onAlbumClick = { album: AlbumUI ->
                navController.navigate(Screen.Album.createRoute(album.id))
            }
        )
    }


        /* PROFILE */
        composable(
            route = Screen.Profile.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")

            // Desempacar Result<UserUI> -> UserUI?
            val user: UserUI? = userId?.let { id ->
                UserRepository.getUserById(id).getOrNull()
            }

            // Desempacar Result<List<ReviewInfo>> -> List<ReviewInfo>
            val reviews: List<ReviewInfo> = user
                ?.let { u -> ReviewRepository.getReviewsByUser(u.id).getOrElse { emptyList() } }
                ?: emptyList()

            val vm: UserProfileViewModel = hiltViewModel()

            if (user != null) {
                UserProfileScreen(
                    viewModel = vm,
                    user = user,                 // ahora es UserUI
                    reviews = reviews,           // ahora es List<ReviewInfo>
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
            val vm: ContentViewModel = hiltViewModel()
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
            val vm: ContentViewModel = hiltViewModel()
            LaunchedEffect(Unit) { vm.setInitial(artistId = null, isOwner = true) }
            ContentScreen(
                viewModel = vm,
                onBack = { navController.navigateUp() },
                onOpenAlbum = { id -> navController.navigate(Screen.Album.createRoute(id)) },
                onEditAlbum = { /* abrir editor si lo implementas */ }
            )
        }

        /* SETTINGS */
        composable(Screen.Settings.route) {
            val vm: SettingsViewModel = hiltViewModel()
            SettingsScreen(
                viewModel = vm,
                onBackClick = { navController.navigateUp() }
            )
        }

        /* EDIT PROFILE */
        composable(Screen.EditProfile.route) {
            val vm: EditProfileViewModel = hiltViewModel()
            EditarPerfilScreen(
                viewModel = vm,
                onBack = { navController.navigateUp() },
                onSaved = { navController.navigateUp() }
            )
        }

        /* ADD REVIEW */
        composable(Screen.AddReview.route) {
            val vm: AddReviewViewModel = hiltViewModel()
            AddReviewScreen(
                viewModel = vm,
                albumList = AlbumRepository.albums, // lista de álbumes para el dropdown
                onCancel = { navController.navigateUp() },
                onPublished = { album, reviewText, score, liked ->

                    ReviewRepository.addReview(
                        album = album,
                        user = UserRepository.users[6],
                        content = reviewText,
                        score = score.toDouble(),
                        isLowScore = !liked
                    )
                    navController.navigateUp()
                }
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
