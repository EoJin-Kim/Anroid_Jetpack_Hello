package com.ej.anroid_jetpack_hello.xylophone

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun XylophoneScreen(viewModel : XylophoneViewModel){
    val keys = listOf(
        Pair("도", Color.Red),
        Pair("레", Color.Yellow),
        Pair("미", Color.Green),
        Pair("파", Color.Blue),
        Pair("소", Color.Gray),
        Pair("라", Color.Red),
        Pair("시", Color.Yellow),
        Pair("도", Color.Green),
    )

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        keys.forEachIndexed{ index, key ->
            val padding = (index + 2) * 8
            KeyBoard(
                text = key.first,
                color = key.second,
                modifier = Modifier.padding(top = padding.dp, bottom = padding.dp)
                    .clickable {
                               viewModel.playSound(index)
                    },
            )
        }
    }
}

@Composable
fun KeyBoard(
    text: String,
    color: Color,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .width(50.dp)
            .fillMaxHeight()
            .background(color = color),
    ){
        Text(
            text = text,
            style = TextStyle(color = Color.White, fontSize = 20.sp),
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
