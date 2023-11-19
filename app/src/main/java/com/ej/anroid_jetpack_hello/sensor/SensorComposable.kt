package com.ej.anroid_jetpack_hello.sensor

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun TiltScreen(x: Float, y : Float){
    val yCoord = x * 20
    val xCoord = y * 20

    Canvas(modifier = Modifier.fillMaxSize()){
        val centerX = size.width / 2
        val centerY = size.height / 2

        // 바깥 원
        drawCircle(
            color = Color.Black,
            radius =  100f,
            center = Offset(centerX, centerY),
            style = Stroke()
        )

        // 녹색 원
        drawCircle(
            color = Color.Green,
            radius =  100f,
            center = Offset(xCoord + centerX, yCoord + centerY),
        )

        // 가운데 십자가
        drawLine(
            color = Color.Black,
            start = Offset(centerX - 20, centerY),
            end = Offset(centerX+ 20, centerY)
        )

        drawLine(
            color = Color.Black,
            start = Offset(centerX, centerY-20),
            end = Offset(centerX, centerY+20)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Preview(){
    TiltScreen(x = 30f , y =20f)
}