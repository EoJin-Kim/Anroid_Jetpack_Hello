package com.ej.anroid_jetpack_hello

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ej.anroid_jetpack_hello.album.AlbumViewModel
import com.ej.anroid_jetpack_hello.album.HomeScreen
import com.ej.anroid_jetpack_hello.album.PermissionRequestScreen
import com.ej.anroid_jetpack_hello.sensor.SensorViewModel
import com.ej.anroid_jetpack_hello.sensor.TiltScreen
import com.ej.anroid_jetpack_hello.webview.WebViewHomeScreen
import com.ej.anroid_jetpack_hello.webview.WebViewViewModel
import com.ej.anroid_jetpack_hello.xylophone.XylophoneScreen
import com.ej.anroid_jetpack_hello.xylophone.XylophoneViewModel

class MainActivity : ComponentActivity() {
    
    private val viewModel by viewModels<SensorViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContent {
            val viewModel = viewModel<XylophoneViewModel>()

            XylophoneScreen(viewModel = viewModel)
        }
    }
}


