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
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.proyecto_movil.core.ServiceLocator
import com.example.proyecto_movil.feature.profile.ui.EditProfileViewModel

@Composable
fun EditProfileRoute(
    onBackClick: () -> Unit,
    onBack: () -> Unit
) {
    val vm: EditProfileViewModel = viewModel(factory = viewModelFactory {
        initializer { EditProfileViewModel(ServiceLocator.profileRepository) }
    })
    val uiState = vm.state.collectAsStateWithLifecycle().value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        val msg = uiState.errorMessage
        if (msg != null) {
            snackbarHostState.showSnackbar(message = msg)
        }
    }

    EditarPerfil(
        onBackClick = onBackClick,
        onBack = onBack,
        nombrePersona = uiState.nombrePersona,
        nombreUsuario = uiState.nombreUsuario,
        email = uiState.email,
        password = "",
        onNombrePersonaChange = vm::onNombrePersonaChange,
        onNombreUsuarioChange = vm::onNombreUsuarioChange,
        onEmailChange = vm::onEmailChange,
        onPasswordChange = { /* managed internally or add to state if needed */ }
    )

    if (uiState.isSaving) {
        CircularProgressIndicator()
    }

    SnackbarHost(hostState = snackbarHostState) { data ->
        Text(text = data.visuals.message)
    }
}

