package com.example.todoapplication.domain.dao

import androidx.room.*
import com.example.todoapplication.domain.entity.Employee
import com.example.todoapplication.domain.entity.Specialty


@Dao
interface EmployeeDao {
    @Query("SELECT * FROM Specialty, Employee WHERE specialtyId = :specialtyId AND userId = Employee.id")
    fun getAllEmployeeBySpecialty(specialtyId: Int): List<Specialty>

    @Query("SELECT * FROM Employee WHERE id = :id")
    fun getEmployeeById(id: Long): Employee

    @Query("SELECT * FROM Employee")
    fun getAllEmployee(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: Employee): Long

    @Delete
    fun dropEmployeeTable(employee: List<Employee>)

    @Delete
    fun dropSpecialtyTable(specialty: List<Specialty>)
}