package com.ej.anroid_jetpack_hello.todolist.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ej.anroid_jetpack_hello.R
import com.ej.anroid_jetpack_hello.todolist.domain.model.Todo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TodoItem(
    todo: Todo,
    onClick: (todo: Todo) -> Unit = {},
    onDeleteClick: (todo : Todo) -> Unit = {}
){
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    Row(

    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_delete_24),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .padding(8.dp)
                .clickable { onDeleteClick(todo) },
        )
        Column(
            modifier = Modifier.weight(1f)
                .clickable {
                    onClick(todo)
                }
        ) {
            Text(
                text = format.format(Date(todo.date)),
                color = if (todo.isDone) Color.Gray else Color.Black,
                style = TextStyle(
                    textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None,
                ),
            )
            Text(
                text = todo.title,
                color = if (todo.isDone) Color.Gray else Color.Black,
                style = TextStyle(
                    textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None,
                ),
            )


        }
        if(todo.isDone){
            Icon(
                painter = painterResource(id = R.drawable.baseline_done_outline_24),
                contentDescription = null,
                tint = Color.Blue,
            )

        }
    }
}