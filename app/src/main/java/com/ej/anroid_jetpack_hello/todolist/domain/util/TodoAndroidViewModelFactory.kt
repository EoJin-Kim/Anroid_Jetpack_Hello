package com.ej.anroid_jetpack_hello.todolist.domain.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ej.anroid_jetpack_hello.todolist.data.repository.TodoRepositoryImpl
import com.ej.anroid_jetpack_hello.todolist.domain.repository.TodoRepository
import com.ej.anroid_jetpack_hello.todolist.main.TodoViewModel

class TodoAndroidViewModelFactory(
    private val application: Application,
    private val todoRepository: TodoRepository = TodoRepositoryImpl(application),
)  : ViewModelProvider.AndroidViewModelFactory(application){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)){
            return TodoViewModel(application = application, todoRepository = todoRepository) as T
        }
        return super.create(modelClass)
    }
}