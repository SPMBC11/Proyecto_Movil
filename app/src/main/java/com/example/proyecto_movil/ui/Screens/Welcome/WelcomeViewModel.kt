package com.example.proyecto_movil.ui.Screens.Welcome

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WelcomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WelcomeState())
    val uiState: StateFlow<WelcomeState> = _uiState

    fun onStartClicked() = _uiState.update { s -> s.copy(navigateNext = true) }
    fun consumeNavigation() = _uiState.update { s -> s.copy(navigateNext = false) }
}
