package com.ej.anroid_jetpack_hello.todolist.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ej.anroid_jetpack_hello.todolist.domain.model.Todo


@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase(){

    abstract fun todoDao() : TodoDao
}