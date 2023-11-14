package com.ej.anroid_jetpack_hello.bmi


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ej.anroid_jetpack_hello.bmi.BmiHomeScreen
import com.ej.anroid_jetpack_hello.bmi.BmiResultScreen
import com.ej.anroid_jetpack_hello.bmi.BmiViewModel

class BmiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel = viewModel<BmiViewModel>()

            val bmi = viewModel.bmi.value
            NavHost(navController = navController, startDestination = "home"){
                composable(route = "home"){
                    BmiHomeScreen(){ height, weight ->
                        val bmi = viewModel.bmiCalculate(height, weight)
                        navController.navigate("result")

                    }
                }

                composable(route = "result"){
                    BmiResultScreen(navController, bmi)
                }
            }
        }
    }
}
