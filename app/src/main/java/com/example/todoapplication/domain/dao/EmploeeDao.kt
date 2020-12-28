package com.example.todoapplication.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.Emploee

@Dao
interface EmploeeDao {
    @Query("SELECT * FROM Specialty WHERE specialtyId LIKE :specialtyId")
    fun getAllEmploeeBySpecialty(specialtyId: Int): List<Specialty>

    @Query("SELECT * FROM Emploee WHERE id = :id")
    fun getEmploeeById(id: Long): Emploee

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmploee(emploee: Emploee): Long
}