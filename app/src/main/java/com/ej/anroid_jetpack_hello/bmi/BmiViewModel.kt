package com.ej.anroid_jetpack_hello.bmi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class BmiViewModel : ViewModel() {
    private val _bmi = mutableStateOf<Double>(0.0)
    val bmi : State<Double> = _bmi

    fun bmiCalculate(height : Double, weight : Double) {
        _bmi.value = weight / (height / 100.0).pow(2.0)
    }
}