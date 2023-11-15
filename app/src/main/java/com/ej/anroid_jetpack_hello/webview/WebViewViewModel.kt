package com.ej.anroid_jetpack_hello.webview

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class WebViewViewModel : ViewModel() {
    private val _url = mutableStateOf<String>("https://www.google.com")
    val url : State<String> = _url

    private val _undoSharedFlow = MutableSharedFlow<Boolean>()
    val undoSharedFlow = _undoSharedFlow.asSharedFlow()

    private val _redoSharedFlow = MutableSharedFlow<Boolean>()
    val redoSharedFlow = _redoSharedFlow.asSharedFlow()

    fun setUrl(url: String) {
        _url.value = url
    }

    fun undo() {
        viewModelScope.launch {
            _undoSharedFlow.emit(true)
        }
    }

    fun redo() {
        viewModelScope.launch {
            _redoSharedFlow.emit(false)
        }
    }
}