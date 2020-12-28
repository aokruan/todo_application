package com.example.todoapplication.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapplication.domain.dao.EmploeeDao
import com.example.todoapplication.domain.dao.SpecaltyDao
import com.example.todoapplication.domain.entity.Emploee
import com.example.todoapplication.domain.entity.Specialty

@Database(entities = [Emploee::class, Specialty::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emploeeDao(): EmploeeDao
    abstract fun specialtyDao(): SpecaltyDao
}