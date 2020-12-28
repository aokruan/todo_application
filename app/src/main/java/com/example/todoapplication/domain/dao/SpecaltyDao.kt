package com.example.todoapplication.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapplication.domain.entity.Specialty

@Dao
interface SpecaltyDao {
    @Query("SELECT * FROM Specialty GROUP BY name")
    fun getAllSpecialty(): LiveData<List<Specialty>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialty(specialty: Specialty): Long
}