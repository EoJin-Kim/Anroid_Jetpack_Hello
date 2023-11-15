package com.ej.anroid_jetpack_hello.stopwatch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ej.anroid_jetpack_hello.R


@Composable
fun StopWatchScreen(
    sec: Int,
    milli: Int,
    isRunning: Boolean,
    lapTimes : List<String>,
    onRest: () -> Unit,
    onToggle : (Boolean) -> Unit,
    onLapTime : () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "StopWatch")}
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "$sec", fontSize = 100.sp)
                Text(text = "$milli", fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
            ) {
                for (lapTime in lapTimes) {
                    Text(text = lapTime)
                }
            }
            Row(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = {
                        onRest()
                    },
                    backgroundColor = Color.Red
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_baseline_refresh_24), contentDescription = "reset")
                }

                FloatingActionButton(
                    onClick = {
                        onToggle(isRunning)
                    },
                    backgroundColor = Color.Green
                ) {
                    Image(
                        painter = painterResource(id = if (isRunning) R.drawable.ic_baseline_pause_24 else R.drawable.ic_baseline_play_arrow_24),
                        contentDescription = "reset/pause"
                    )
                }

                Button(
                    onClick = {
                        onLapTime()
                    },
                ) {
                    Text(text = "랩 타임")
                }
            }
        }

    }
}