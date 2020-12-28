package com.example.todoapplication.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapplication.domain.dao.UserDao
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.User

@Database(entities = [User::class, Specialty::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}