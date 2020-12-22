package com.example.todoapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.todoapplication.domain.dao.TodoDao
import com.example.todoapplication.domain.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(private val todoDao: TodoDao) {

    val getAllTodo = todoDao.getAll()

    suspend fun insert(todo: Todo) {
        withContext(Dispatchers.IO) {
            todoDao.insert(todo)
        }
    }
}