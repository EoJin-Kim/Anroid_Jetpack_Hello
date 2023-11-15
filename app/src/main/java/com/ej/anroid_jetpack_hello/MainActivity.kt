package com.ej.anroid_jetpack_hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ej.anroid_jetpack_hello.stopwatch.StopWatchScreen
import com.ej.anroid_jetpack_hello.stopwatch.StopWatchViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<StopWatchViewModel>()

            val sec = viewModel.sec.value
            val milli = viewModel.milli.value
            val isRunning = viewModel.isRunning.value
            val lapTimes = viewModel.lapTimes.value

            StopWatchScreen(sec = sec,
                milli = milli,
                isRunning = isRunning,
                lapTimes = lapTimes,
                onRest = { viewModel.reset() },
                onToggle = {
                    if(it) viewModel.pause() else viewModel.start()
                },
                onLapTime = {
                    viewModel.recordLapTime()
                }
            )
        }
    }
}
