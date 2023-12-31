package com.ej.anroid_jetpack_hello.todolist.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ej.anroid_jetpack_hello.R
import com.ej.anroid_jetpack_hello.todolist.main.components.TodoItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoScreen(
    viewModel: TodoViewModel,
){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "오늘 할 일")}
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(), value = text,
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text(text = "할일")},
                onValueChange = {
                    text = it
                },
                trailingIcon = {
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_refresh_24), contentDescription = null)
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.addTodo(text)
                    text = ""
                    keyboardController?.hide()
                })
            )
            Divider()
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){

                items(viewModel.items.value){ todoItem ->
                    Column {
                        TodoItem(
                            todo = todoItem,
                            onClick = { todo ->
                                viewModel.toggle(todo.uid)
                            },
                            onDeleteClick = { todo ->
                                viewModel.delete(todo.uid)

                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "할 일 살제됨",
                                        actionLabel = "취소",
                                    )
                                    if(result == SnackbarResult.ActionPerformed){
                                        viewModel.restoreTodo()
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Divider(color = Color.Black, thickness = 1.dp)
                    }
                }
            }
        }
    }
}