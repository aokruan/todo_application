package com.example.todoapplication.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapplication.domain.dao.EmployeeDao
import com.example.todoapplication.domain.dao.SpecaltyDao
import com.example.todoapplication.domain.entity.Employee
import com.example.todoapplication.domain.entity.Specialty

@Database(entities = [Employee::class, Specialty::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    abstract fun specialtyDao(): SpecaltyDao
}