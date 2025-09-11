package com.example.proyecto_movil.ui.Screens.Welcome

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(WelcomeState())
    val uiState: StateFlow<WelcomeState> = _uiState

    fun onStartClicked() = _uiState.update { s -> s.copy(navigateNext = true) }
    fun consumeNavigation() = _uiState.update { s -> s.copy(navigateNext = false) }
}
