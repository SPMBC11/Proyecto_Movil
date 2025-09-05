package com.example.proyecto_movil.uiViews.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, throwable ->
        onCoroutineError(throwable)
    }

    protected open fun onCoroutineError(t: Throwable) {
        // Log simple; puedes conectar a Crashlytics si deseas
        t.printStackTrace()
    }

    protected fun launchSafely(block: suspend () -> Unit): Job {
        return viewModelScope.launch(handler) { block() }
    }
}