package com.ej.anroid_jetpack_hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ej.anroid_jetpack_hello.bmi.BmiHomeScreen
import com.ej.anroid_jetpack_hello.bmi.BmiResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home"){
                composable(route = "home"){
                    BmiHomeScreen(navController)
                }

                composable(route = "result"){
                    BmiResultScreen(navController, 35.0)
                }
            }
        }
    }
}
