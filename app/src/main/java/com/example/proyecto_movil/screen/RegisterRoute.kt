package com.example.proyecto_movil.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_movil.feature.auth.ui.RegisterViewModel

@Composable
fun RegisterRoute(
    onBack: () -> Unit,
    onRegistered: () -> Unit,
    onLogin: () -> Unit
) {
    val vm: RegisterViewModel = viewModel()
    val uiState = vm.state.collectAsStateWithLifecycle().value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        val msg = uiState.errorMessage
        if (msg != null) {
            snackbarHostState.showSnackbar(message = msg)
            vm.clearError()
        }
    }

    LaunchedEffect(uiState.isRegistered) {
        if (uiState.isRegistered) onRegistered()
    }

    RegisterScreen2(
        onBack = onBack,
        onRegister = { username, email, password ->
            vm.registerUser(username, email, password)
        },
        onLogin = onLogin
    )

    if (uiState.isLoading) {
        CircularProgressIndicator()
    }

    SnackbarHost(hostState = snackbarHostState) { data ->
        Text(text = data.visuals.message)
    }
}

