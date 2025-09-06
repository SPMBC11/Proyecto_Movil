package com.example.proyecto_movil.ui.Screens.EditProfile

import com.example.proyecto_movil.R

data class EditProfileState(
    val avatarRes: Int = R.drawable.xocas,
    val nombrePersona: String = "",
    val nombreUsuario: String = "",
    val email: String = "",
    val password: String = "",
    val mostrarPassword: Boolean = false,
    val showMessage: Boolean = false,
    val errorMessage: String = "",
    val navigateBack: Boolean = false,
    val navigateSaved: Boolean = false
)