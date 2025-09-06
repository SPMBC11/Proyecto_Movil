package com.example.proyecto_movil.ui.Screens.Settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.ClickableSectionTitle
import com.example.proyecto_movil.ui.utils.ScreenBackground

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.navigateBack) {
        if (state.navigateBack) {
            onBackClick()
            viewModel.consumeNavigation()
        }
    }

    val isDarkSystem = isSystemInDarkTheme()
    val useDarkBackground = state.preferDarkMode || isDarkSystem
    val backgroundRes = if (useDarkBackground) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            item {
                Header(
                    onBackClick = { viewModel.onBackClicked() },
                    onLogoutClick = { viewModel.onLogoutClicked() }
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                ProfileSection(
                    username = state.username,
                    avatarRes = state.avatarRes,
                    onViewProfile = { viewModel.onViewProfileClicked() }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                SettingsSection(
                    preferDarkMode = state.preferDarkMode,
                    onToggleDarkMode = { viewModel.togglePreferDarkMode() },
                    onLanguageClick = { viewModel.onLanguageClicked() }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                PrivacySection(
                    hideActivity = state.hideActivity,
                    showRecentAlbums = state.showRecentAlbums,
                    publicPlaylists = state.publicPlaylists,
                    showPlaylistsOnProfile = state.showPlaylistsOnProfile,
                    showFollowersAndFollowing = state.showFollowersAndFollowing,
                    onToggleHideActivity = { viewModel.toggleHideActivity() },
                    onToggleShowRecentAlbums = { viewModel.toggleShowRecentAlbums() },
                    onTogglePublicPlaylists = { viewModel.togglePublicPlaylists() },
                    onToggleShowPlaylistsOnProfile = { viewModel.toggleShowPlaylistsOnProfile() },
                    onToggleShowFollowersAndFollowing = { viewModel.toggleShowFollowersAndFollowing() }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                ContentSection(
                    allowExplicitContent = state.allowExplicitContent,
                    showUnavailableInCountry = state.showUnavailableInCountry,
                    onToggleAllowExplicitContent = { viewModel.toggleAllowExplicitContent() },
                    onToggleShowUnavailableInCountry = { viewModel.toggleShowUnavailableInCountry() }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                NotificationsSection(
                    pushNotifications = state.pushNotifications,
                    emailNotifications = state.emailNotifications,
                    onTogglePush = { viewModel.togglePushNotifications() },
                    onToggleEmail = { viewModel.toggleEmailNotifications() }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                DeactivateAccountButton(onClick = { viewModel.onDeactivateAccountClicked() })
            }
        }
    }
}

@Composable
private fun Header(
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Volver",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(30.dp)
                .clickable { onBackClick() }
        )
        Text(
            text = "Configuración",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Filled.ExitToApp,
            contentDescription = "Cerrar sesión",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(30.dp)
                .clickable { onLogoutClick() }
        )
    }
}

@Composable
private fun ProfileSection(
    username: String,
    avatarRes: Int,
    onViewProfile: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = username,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Ver perfil",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { onViewProfile() }
            )
        }
    }
}

@Composable
private fun SettingsSection(
    preferDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    onLanguageClick: () -> Unit
) {
    ClickableSectionTitle(title = "Preferencias")
    Spacer(modifier = Modifier.height(16.dp))
    SettingItem(
        text = "Idioma de la aplicación",
        hasSwitch = false,
        onClick = onLanguageClick
    )
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem(
        text = "Modo oscuro",
        hasSwitch = true,
        checked = preferDarkMode,
        onCheckedChange = { onToggleDarkMode() }
    )
}

@Composable
private fun PrivacySection(
    hideActivity: Boolean,
    showRecentAlbums: Boolean,
    publicPlaylists: Boolean,
    showPlaylistsOnProfile: Boolean,
    showFollowersAndFollowing: Boolean,
    onToggleHideActivity: () -> Unit,
    onToggleShowRecentAlbums: () -> Unit,
    onTogglePublicPlaylists: () -> Unit,
    onToggleShowPlaylistsOnProfile: () -> Unit,
    onToggleShowFollowersAndFollowing: () -> Unit
) {
    ClickableSectionTitle(title = "Privacidad y social")
    Spacer(modifier = Modifier.height(16.dp))
    SettingItem("Ocultar tu actividad", true, hideActivity) { onToggleHideActivity() }
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem("Álbumes escuchados recientemente", true, showRecentAlbums) { onToggleShowRecentAlbums() }
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem("Playlists públicas", true, publicPlaylists) { onTogglePublicPlaylists() }
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem("Playlists en tu perfil", true, showPlaylistsOnProfile) { onToggleShowPlaylistsOnProfile() }
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem("Mostrar seguidores y seguidos", true, showFollowersAndFollowing) { onToggleShowFollowersAndFollowing() }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Usuarios bloqueados",
        color = MaterialTheme.colorScheme.error,
        fontSize = 16.sp,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable { }
    )
}

@Composable
private fun ContentSection(
    allowExplicitContent: Boolean,
    showUnavailableInCountry: Boolean,
    onToggleAllowExplicitContent: () -> Unit,
    onToggleShowUnavailableInCountry: () -> Unit
) {
    ClickableSectionTitle(title = "Contenido y visualización")
    Spacer(modifier = Modifier.height(16.dp))
    SettingItem("Permitir contenido explícito", true, allowExplicitContent) { onToggleAllowExplicitContent() }
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem("Mostrar contenido no disponible en mi país", true, showUnavailableInCountry) { onToggleShowUnavailableInCountry() }
}

@Composable
private fun NotificationsSection(
    pushNotifications: Boolean,
    emailNotifications: Boolean,
    onTogglePush: () -> Unit,
    onToggleEmail: () -> Unit
) {
    ClickableSectionTitle(title = "Notificaciones")
    Spacer(modifier = Modifier.height(16.dp))
    SettingItem("Notificaciones push", true, pushNotifications) { onTogglePush() }
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem("Notificaciones por correo", true, emailNotifications) { onToggleEmail() }
}

@Composable
private fun SettingItem(
    text: String,
    hasSwitch: Boolean,
    checked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (!hasSwitch && onClick != null) Modifier.clickable { onClick() } else Modifier
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp
        )
        if (hasSwitch) {
            Switch(
                checked = checked,
                onCheckedChange = { onCheckedChange?.invoke(it) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    }
}

@Composable
private fun DeactivateAccountButton(onClick: () -> Unit) {
    Text(
        text = "Desactivar mi cuenta",
        color = MaterialTheme.colorScheme.primary,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .clickable { onClick() }
    )
}

@Preview(showBackground = true, name = "Settings Light", showSystemUi = true)
@Composable
fun SettingsScreenLightPreview() {
    Proyecto_movilTheme(useDarkTheme = false) {
        val vm = remember { SettingsViewModel() }
        SettingsScreen(viewModel = vm)
    }
}

@Preview(showBackground = true, name = "Settings Dark", showSystemUi = true)
@Composable
fun SettingsScreenDarkPreview() {
    Proyecto_movilTheme(useDarkTheme = true) {
        val vm = remember { SettingsViewModel() }
        SettingsScreen(viewModel = vm)
    }
}