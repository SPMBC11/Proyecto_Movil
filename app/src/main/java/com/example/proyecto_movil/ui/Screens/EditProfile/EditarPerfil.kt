package com.example.proyecto_movil.ui.Screens.EditProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.BotonEditarImagen
import com.example.proyecto_movil.ui.utils.BotonGuardar
import com.example.proyecto_movil.ui.utils.ScreenBackground

@Composable
fun EditarPerfilScreen(
    viewModel: EditProfileViewModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onSaved: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.navigateBack, state.navigateSaved) {
        if (state.navigateBack) {
            onBack()
            viewModel.consumeBack()
        }
        if (state.navigateSaved) {
            onSaved()
            viewModel.consumeSaved()
        }
    }

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { viewModel.onBackClicked() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    text = "Editar perfil",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(48.dp))
            }

            Spacer(Modifier.height(16.dp))

            Image(
                painter = painterResource(id = state.avatarRes),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(8.dp))

            BotonEditarImagen(
                "Editar imagen",
                onClick = { },
                modifier = Modifier
                    .height(32.dp)
                    .width(150.dp)
            )

            Spacer(Modifier.height(24.dp))

            CompactTextField(
                value = state.nombrePersona,
                onValueChange = viewModel::updateNombrePersona,
                label = stringResource(R.string.nombre),
                icon = R.drawable.usuario
            )

            CompactTextField(
                value = state.nombreUsuario,
                onValueChange = viewModel::updateNombreUsuario,
                label = stringResource(R.string.nombre_usuario),
                icon = R.drawable.usuario
            )

            CompactTextField(
                value = state.email,
                onValueChange = viewModel::updateEmail,
                label = stringResource(R.string.email),
                icon = R.drawable.correo
            )

            CompactTextField(
                value = state.password,
                onValueChange = viewModel::updatePassword,
                label = stringResource(R.string.contra),
                icon = R.drawable.candado,
                isPassword = true,
                mostrarPassword = state.mostrarPassword,
                onTogglePassword = viewModel::toggleMostrarPassword
            )

            if (state.showMessage) {
                Spacer(Modifier.height(6.dp))
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 13.sp
                )
            }

            Spacer(Modifier.height(24.dp))

            BotonGuardar(
                "Guardar",
                onClick = { viewModel.onSaveClicked() },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CompactTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: Int,
    isPassword: Boolean = false,
    mostrarPassword: Boolean = false,
    onTogglePassword: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 14.sp) },
        leadingIcon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        },
        trailingIcon = if (isPassword && onTogglePassword != null) {
            {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        imageVector = if (mostrarPassword) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        } else null,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
        visualTransformation = if (isPassword && !mostrarPassword)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(vertical = 6.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Preview(name = "Light Mode", showSystemUi = true)
@Composable
fun EditarPerfilPreviewLight() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { EditProfileViewModel() }
            EditarPerfilScreen(
                viewModel = vm
            )
        }
    }
}

@Preview(name = "Dark Mode", showSystemUi = true)
@Composable
fun EditarPerfilPreviewDark() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { EditProfileViewModel() }
            EditarPerfilScreen(
                viewModel = vm
            )
        }
    }
}