package com.ej.anroid_jetpack_hello.sensor

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel

class SensorViewModel(application: Application) : AndroidViewModel(application), SensorEventListener, LifecycleObserver{


    private val _x = mutableStateOf<Float>(0f)
    val x : State<Float> = _x

    private val _y = mutableStateOf<Float>(0f)
    val y : State<Float> = _y

    private val sensorManager by lazy {
        application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun registerSensor() {
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL,

        )

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun unregisterSensor() {
        sensorManager.unregisterListener(this)
    }
    override fun onSensorChanged(p0: SensorEvent?) {
        p0?.let {
            _x.value = it.values[0]
            _y.value = it.values[1]
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}