package com.ej.anroid_jetpack_hello.todolist.main

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ej.anroid_jetpack_hello.todolist.domain.model.Todo
import com.ej.anroid_jetpack_hello.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(application: Application, private val todoRepository: TodoRepository) :
    AndroidViewModel(application) {

    private val _items = mutableStateOf<List<Todo>>(emptyList<Todo>())
    val items: State<List<Todo>> = _items

    private var recentlyDeleteTodo : Todo? = null

    init {
        viewModelScope.launch {
            todoRepository.observeTodos()
                .collect { todos ->
                    _items.value = todos
                }
        }
    }
    fun addTodo(text: String) {
        viewModelScope.launch {
            todoRepository.addTodo(Todo(title = text))
        }
    }

    fun toggle(uid: Int) {
        val todo = items.value.find { todo: Todo ->
            todo.uid == uid
        }
        todo?.let {
            viewModelScope.launch {
                todoRepository.updateTodo(it.copy(isDone = !it.isDone).apply {
                    this.uid = it.uid
                })
            }
        }
    }

    fun delete(uid: Int) {
        val todo = items.value.find { todo: Todo ->
            todo.uid == uid
        }

        todo?.let {
            viewModelScope.launch {
                todoRepository.deleteTodo(it);
                recentlyDeleteTodo = it
            }
        }
    }

    fun restoreTodo(){
        viewModelScope.launch {
            todoRepository.addTodo(recentlyDeleteTodo ?: return@launch)
            recentlyDeleteTodo = null
        }
    }
}