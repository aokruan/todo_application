package com.example.todoapplication.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapplication.domain.dao.TodoDao
import com.example.todoapplication.domain.dao.UserDao
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.domain.entity.UsersBase

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
    //abstract fun userDao(): UserDao
}