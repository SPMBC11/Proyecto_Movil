package com.example.proyecto_movil.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_movil.core.ServiceLocator
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.proyecto_movil.feature.auth.ui.LoginViewModel

@Composable
fun LoginRoute(
    onBack: () -> Unit,
    onLoggedIn: () -> Unit,
    onForgotPassword: () -> Unit,
    onRegister: () -> Unit,
    onGoogle: () -> Unit,
    onFacebook: () -> Unit,
    onApple: () -> Unit
) {
    val vm: LoginViewModel = viewModel(factory = viewModelFactory {
        initializer { LoginViewModel(ServiceLocator.authRepository) }
    })

    val uiState = androidx.lifecycle.compose.collectAsStateWithLifecycle(vm.state).value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        val msg = uiState.errorMessage
        if (msg != null) {
            snackbarHostState.showSnackbar(message = msg)
            vm.clearError()
        }
    }

    LoginScreen(
        onBack = onBack,
        onLogin = { email, password, remember -> vm.login(email, password, remember) },
        onForgotPassword = onForgotPassword,
        onRegister = onRegister,
        onGoogle = onGoogle,
        onFacebook = onFacebook,
        onApple = onApple
    )

    if (uiState.isLoading) {
        CircularProgressIndicator()
    }

    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) onLoggedIn()
    }

    SnackbarHost(hostState = snackbarHostState) { data ->
        Text(text = data.visuals.message)
    }
}

