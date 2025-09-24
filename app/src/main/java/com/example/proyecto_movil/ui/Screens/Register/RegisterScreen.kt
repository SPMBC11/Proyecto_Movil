package com.example.proyecto_movil.ui.Screens.Register

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.utils.AppButton
import com.example.proyecto_movil.ui.utils.LogoApp
import com.example.proyecto_movil.ui.utils.Registrate
import com.example.proyecto_movil.ui.utils.Terminos
import com.example.proyecto_movil.ui.utils.YatienesCuenta

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onRegister: (String, String, String) -> Unit = { _, _, _ -> },
    onLogin: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    // ---------- Snackbar in-app ----------
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.showMessage, state.errorMessage) {
        if (state.showMessage && state.errorMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(state.errorMessage)
            viewModel.consumeMessage() // ✅ importante: consumir el evento
        }
    }

    // ---------- Navegaciones one-shot ----------
    LaunchedEffect(state.navigateBack, state.navigateToLogin, state.navigateAfterRegister) {
        when {
            state.navigateBack -> {
                onBack()
                viewModel.consumeNavigation()
            }
            state.navigateToLogin -> {
                onLogin()
                viewModel.consumeNavigation()
            }
            state.navigateAfterRegister -> {
                onRegister(state.nombreUsuario, state.email, state.password)
                viewModel.consumeNavigation()
            }
        }
    }

    // ---------- UI envuelta en Scaffold para poder mostrar Snackbar ----------
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(modifier = modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            Image(
                painter = painterResource(id = backgroundRes),
                contentDescription = stringResource(id = R.string.fondo_degradado),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { viewModel.onBackClicked() },
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(Modifier.height(80.dp))
                LogoApp()
                Spacer(Modifier.height(60.dp))
                Registrate(texto = "Regístrate")
                Spacer(Modifier.height(10.dp))

                FormularioRegistro(
                    nombrePersona = state.nombrePersona,
                    nombreUsuario = state.nombreUsuario,
                    email = state.email,
                    password = state.password,
                    mostrarPassword = state.mostrarPassword,
                    onNombrePersonaChange = viewModel::updateNombrePersona,
                    onNombreUsuarioChange = viewModel::updateNombreUsuario,
                    onEmailChange = viewModel::updateEmail,
                    onPasswordChange = viewModel::updatePassword,
                    onTogglePassword = viewModel::toggleMostrarPassword
                )

                Spacer(Modifier.height(30.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = state.acceptedTerms,
                        onCheckedChange = { viewModel.toggleAcceptedTerms() }
                    )
                    Spacer(Modifier.width(8.dp))
                    Terminos(
                        texto = "He leído y acepto los términos y condiciones",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(16.dp))

                AppButton(
                    text = "Registrarse",
                    onClick = { viewModel.onRegisterClicked() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(Modifier.height(30.dp))
                YatienesCuenta(
                    texto = "¿Ya tienes una cuenta? Inicia sesión",
                    onClick = { viewModel.onLoginClicked() }
                )
            }
        }
    }
}

@Composable
private fun FormularioRegistro(
    nombrePersona: String,
    nombreUsuario: String,
    email: String,
    password: String,
    mostrarPassword: Boolean,
    onNombrePersonaChange: (String) -> Unit,
    onNombreUsuarioChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePassword: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            value = nombrePersona,
            onValueChange = onNombrePersonaChange,
            label = { Text(stringResource(R.string.nombre)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.usuario),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icono de usuario",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nombreUsuario,
            onValueChange = onNombreUsuarioChange,
            label = { Text(stringResource(R.string.nombre_usuario)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.usuario),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icono de usuario",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text(stringResource(R.string.email)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.correo),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icono de correo",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.contra)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.candado),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icono de candado",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            visualTransformation = if (mostrarPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        painter = painterResource(if (mostrarPassword) R.drawable.view else R.drawable.hide),
                        contentDescription = stringResource(R.string.mostrar_password),
                        modifier = Modifier.size(22.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
}
