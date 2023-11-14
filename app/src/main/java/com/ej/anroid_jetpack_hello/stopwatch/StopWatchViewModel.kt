package com.ej.anroid_jetpack_hello.stopwatch

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.Timer
import kotlin.concurrent.timer

class StopWatchViewModel : ViewModel() {
    private var time = 0;
    private var timerTask : Timer? = null
    private val _isRunning = mutableStateOf<Boolean>(false)
    val isRunning : State<Boolean> = _isRunning

    private val _sec = mutableStateOf<Int>(0)
    val sec : State<Int> = _sec

    private val _milli = mutableStateOf<Int>(0)
    val milli : State<Int> = _milli

    private val _lapTimes = mutableStateOf(mutableListOf<String>())
    val lapTimes : State<List<String>> = _lapTimes

    private var lap = 1

    fun start(){
        _isRunning.value = true
        timerTask =timer(period = 10){
            time++;
            _sec.value = time/ 100
            _milli.value = time % 100
        }
    }

    fun pause(){
        _isRunning.value = false
        timerTask?.cancel()
    }

    fun reset() {
        _isRunning.value = false
        timerTask?.cancel()

        time = 0;
        _sec.value = 0;
        _milli.value = 0;
        lap = 1
        _lapTimes.value.clear()
    }

    fun recordLapTime(){
        val  temp = _lapTimes.value.add(0,"$lap LAP : ${sec.value}.${milli.value}")
        lap ++
    }
}