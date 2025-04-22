package com.example.junittestapp

import android.app.Application
import com.example.junittestapp.data.TodoRepository
import com.example.junittestapp.data.TodoRoomDatabase
import com.example.junittestapp.data.TodoRoomRepository

class TodoApplication : Application() {

    val todoRepository: TodoRepository
        get() = TodoRoomRepository(TodoRoomDatabase.getInstance(this.applicationContext)!!.todoDao())
}