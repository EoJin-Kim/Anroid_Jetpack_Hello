package com.ej.anroid_jetpack_hello

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import com.ej.anroid_jetpack_hello.webview.WebViewHomeScreen
import com.ej.anroid_jetpack_hello.webview.WebViewViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = viewModel<AlbumViewModel>()
            var granted by remember {
                mutableStateOf(false)
            }

            val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()){ isGranted ->
                granted = isGranted
            }

            if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            ) == PackageManager.PERMISSION_GRANTED){
                granted = true
            }
            
            if(granted){
                viewModel.fetchPhotos()
                HomeScreen(photoUris = viewModel.photoUris.value)
            }else{
                PermissionRequestScreen {
                    launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }
}


