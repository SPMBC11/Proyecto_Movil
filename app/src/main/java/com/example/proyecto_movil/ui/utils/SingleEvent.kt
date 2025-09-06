package com.example.proyecto_movil.ui.utils

/**
 * Evento de una sola consumición (snackbar, navegación, diálogos).
 */
class SingleEvent<out T>(private val content: T) {
    private var handled = false
    fun getIfNotHandled(): T? = if (handled) null else { handled = true; content }
    fun peek(): T = content
}