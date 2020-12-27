package com.example.todoapplication.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapplication.domain.dao.TodoDao
import com.example.todoapplication.domain.dao.UserDao
import com.example.todoapplication.domain.entity.*

@Database(entities = [Todo::class, User::class, Specialty::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
    abstract fun userDao(): UserDao
}