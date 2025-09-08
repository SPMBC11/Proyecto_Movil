package com.example.proyecto_movil

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_movil.navigation.AppNavHost
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.proyecto_movil.navigation.Screen

@Composable
fun CritiChordApp() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CritiChordTopBar()
        },
        bottomBar = {
            TwitterBottomNavigationBar(navController)
        }
    ) {

        innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AppNavHost(navController = navController)
        }
    }
}

data class BottomNavItem(
    val filledeIcon: ImageVector,
    val outlineIcon: ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem(Icons.Filled.Home, Icons.Outlined.Home, Screen.Home.route),
    BottomNavItem(Icons.Filled.Email, Icons.Outlined.Email, Screen.Settings.route),
    BottomNavItem(Icons.Filled.Person, Icons.Outlined.Person, Screen.Profile.route),
)

@Composable
fun TwitterBottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.filledeIcon else item.outlineIcon,
                        contentDescription = item.route
                    )
                },
                selected = false,
                onClick = {
                    navController.navigate(item.route)
                }

            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CritiChordTopBar() {

    val isDark = isSystemInDarkTheme()
    val logoRes = if (isDark) {
        R.drawable.logo   // logo normal para oscuro
    } else {
        R.drawable.logo_negro  // logo negro para claro
    }

    CenterAlignedTopAppBar(
        title = {
            Row {
                Icon(
                    painter = painterResource(id = logoRes),
                    contentDescription = "Logo CritiChord",
                    tint = Color.Unspecified, // para que respete los colores originales del drawable
                    modifier = Modifier.size(32.dp) // ajusta el tamaño a lo que quieras
                )
                Text(
                    text = "CritiChord",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CritiChordBottomBar(
    navController: NavController
) {

    val isDark = isSystemInDarkTheme()
    val logoRes = if (isDark) {
        R.drawable.logo   // logo normal para oscuro
    } else {
        R.drawable.logo_negro  // logo negro para claro
    }

    CenterAlignedTopAppBar(
        title = {
            Row {
                Icon(
                    painter = painterResource(id = logoRes),
                    contentDescription = "Logo CritiChord",
                    tint = Color.Unspecified, // para que respete los colores originales del drawable
                    modifier = Modifier.size(32.dp) // ajusta el tamaño a lo que quieras
                )
                Text(
                    text = "CritiChord",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    )
}