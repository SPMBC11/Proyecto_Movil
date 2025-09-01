package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui_components.ScreenBackground
import com.example.proyecto_movil.ui_components.SectionTitle

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    ScreenBackground(backgroundRes = R.drawable.fondocriti) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            item {
                Header(onBackClick = onBackClick)
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                ProfileSection()
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                SettingsSection()
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                PrivacySection()
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                ContentSection()
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                NotificationsSection()
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                DeactivateAccountButton()
            }
        }
    }
}

@Composable
private fun Header(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Volver",
            tint = Color.White,
            modifier = Modifier
                .size(30.dp)
                .clickable { onBackClick() }
        )
        Text(
            text = "Configuración",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Filled.ExitToApp,
            contentDescription = "Cerrar sesión",
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
private fun ProfileSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.xocas),
            contentDescription = "Avatar de el.xokas",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "el.xokas",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Ver perfil",
                color = Color.White,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { /* Acción para ver perfil */ }
            )
        }
    }
}

@Composable
private fun SettingsSection() {
    SectionTitle(title = "Preferencias")
    Spacer(modifier = Modifier.height(16.dp))
    SettingItem(text = "Idioma de la aplicación", hasSwitch = false)
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem(text = "Modo oscuro", hasSwitch = true, isChecked = true)
}

@Composable
private fun PrivacySection() {
    SectionTitle(title = "Privacidad y social")
    Spacer(modifier = Modifier.height(16.dp))
    SettingItem(text = "Ocultar tu actividad", hasSwitch = true, isChecked = true)
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem(text = "Álbumes escuchados recientemente", hasSwitch = true, isChecked = true)
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem(text = "Playlists públicas", hasSwitch = true, isChecked = false)
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem(text = "Playlists aparecen en tu perfil", hasSwitch = true, isChecked = true)
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem(text = "Mostrar seguidores y seguidos", hasSwitch = true, isChecked = true)
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Usuarios bloqueados",
        color = Color(0xFFC83838),
        fontSize = 16.sp,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable { /* Acción para ver usuarios bloqueados */ }
    )
}

@Composable
private fun ContentSection() {
    SectionTitle(title = "Contenido y visualización")
    Spacer(modifier = Modifier.height(16.dp))
    SettingItem(text = "Permitir la reproducción de contenido explícito", hasSwitch = true, isChecked = true)
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem(text = "Mostrar contenido no disponible en mi país", hasSwitch = true, isChecked = false)
}

@Composable
private fun NotificationsSection() {
    SectionTitle(title = "Notificaciones")
    Spacer(modifier = Modifier.height(16.dp))
    SettingItem(text = "Notificaciones push", hasSwitch = true, isChecked = true)
    Spacer(modifier = Modifier.height(8.dp))
    SettingItem(text = "Notificaciones por correo", hasSwitch = true, isChecked = true)
}

@Composable
private fun SettingItem(
    text: String,
    hasSwitch: Boolean,
    isChecked: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
        if (hasSwitch) {
            var checked by remember { mutableStateOf(isChecked) }
            Switch(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF1DB954),
                    uncheckedThumbColor = Color.Gray,
                    uncheckedTrackColor = Color.LightGray
                )
            )
        }
    }
}

@Composable
private fun DeactivateAccountButton() {
    Text(
        text = "Desactivar mi cuenta",
        color = Color(0xFF06A0B5),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .clickable { /* Acción para desactivar cuenta */ }
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}