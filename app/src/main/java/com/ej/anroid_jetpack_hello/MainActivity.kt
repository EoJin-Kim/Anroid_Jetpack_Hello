package com.ej.anroid_jetpack_hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ej.anroid_jetpack_hello.webview.WebViewHomeScreen
import com.ej.anroid_jetpack_hello.webview.WebViewViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<WebViewViewModel>()
            WebViewHomeScreen(viewModel)
        }
    }
}
