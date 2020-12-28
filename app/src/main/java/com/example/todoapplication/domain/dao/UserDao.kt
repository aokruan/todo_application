package com.example.todoapplication.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM Specialty WHERE specialtyId LIKE :specialtyId")
    fun getAllUsersBySpecialty(specialtyId: Int): List<Specialty>

    @Query("SELECT * FROM user")
    fun getAll1(): List<User>

    @Query("SELECT * FROM Specialty GROUP BY name")
    fun getAllSpecialtyNoLiveData(): List<Specialty>

    @Query("SELECT * FROM Specialty GROUP BY name")
    fun getAllSpecialty(): LiveData<List<Specialty>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialty(specialty: Specialty): Long

}