package com.ej.anroid_jetpack_hello.bmi

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ej.anroid_jetpack_hello.R

@Composable
fun BmiHomeScreen(navController: NavHostController) {
    var height by rememberSaveable {
        mutableStateOf("")
    }

    var weight by rememberSaveable {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(100.dp),
                title = {
                    Text(text = "비만도 계산기")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = height,
                onValueChange = {
                    height = it
                },
                label = { Text(text = "키") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = weight,
                onValueChange = {
                    weight = it
                },
                label = { Text(text = "몸무게") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    navController.navigate("result")
                },
            ) {
                Text(text = "결과")
            }
        }
    }
}

@Composable
fun BmiResultScreen(navController: NavController, bmi: Double){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "비만ㄷ 계산기")},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "home",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "과체중", fontSize = 30.sp)
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_sentiment_dissatisfied_24),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                colorFilter = ColorFilter.tint(
                    color = Color.Black
                )
            )
        }
    }
}

@Preview
@Composable
fun Preview(){
//  BmiResultScreen(bmi = 42.3)
}