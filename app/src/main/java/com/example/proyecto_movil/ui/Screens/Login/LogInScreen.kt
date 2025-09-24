package com.example.proyecto_movil.ui.Screens.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onBack: () -> Unit = {},
    onLogin: (email: String, password: String, remember: Boolean) -> Unit = { _, _, _ -> },
    onForgotPassword: () -> Unit = {},
    onRegister: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    // ---------- Navegaciones one-shot ----------
    LaunchedEffect(state.navigateBack, state.navigateAfterLogin, state.navigateToForgot, state.navigateToRegister) {
        if (state.navigateBack) { onBack(); viewModel.consumeBack() }
        if (state.navigateAfterLogin) {
            onLogin(state.email, state.password, state.remember)
            viewModel.consumeAfterLogin()
        }
        if (state.navigateToForgot) { onForgotPassword(); viewModel.consumeForgot() }
        if (state.navigateToRegister) { onRegister(); viewModel.consumeRegister() }
    }

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light
    val logoRes = if (isDark) R.drawable.logo else R.drawable.logo_negro

    // ---------- Snackbar in-app (Opción A) ----------
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.showMessage, state.errorMessage) {
        if (state.showMessage && state.errorMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(state.errorMessage)
            viewModel.consumeMessage() // ✅ consumir el evento para que no se repita
        }
    }

    // ---------- UI envuelta en Scaffold para mostrar Snackbar ----------
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Image(
                painter = painterResource(id = backgroundRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { viewModel.onBackClicked() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Atrás",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = logoRes),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Accede a tu cuenta",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(24.dp))

                InputField(
                    value = state.email,
                    onValueChange = viewModel::updateEmail,
                    label = stringResource(R.string.email),
                    isPassword = false,
                    showPassword = false,
                    onTogglePassword = {}
                )

                Spacer(Modifier.height(12.dp))

                InputField(
                    value = state.password,
                    onValueChange = viewModel::updatePassword,
                    label = stringResource(R.string.contra),
                    isPassword = true,
                    showPassword = state.showPassword,
                    onTogglePassword = { viewModel.toggleShowPassword() }
                )

                // ❌ Ya no mostramos el error inline; lo maneja el Snackbar

                Spacer(Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = state.remember,
                        onCheckedChange = { viewModel.toggleRemember() },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            checkmarkColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    Text(
                        stringResource(R.string.recordarme),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp
                    )
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = { viewModel.onLoginClicked() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text("Ingresar", fontWeight = FontWeight.SemiBold)
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.olvidaste_tu_contrase_a),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable { viewModel.onForgotClicked() }
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "¿No tienes una cuenta? Regístrate",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { viewModel.onRegisterClicked() }
                )
            }

        }
    }
}

@Composable
private fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean,
    showPassword: Boolean,
    onTogglePassword: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
        visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showPassword) "Ocultar" else "Mostrar",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
