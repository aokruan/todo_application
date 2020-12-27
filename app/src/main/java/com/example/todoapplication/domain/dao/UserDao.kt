package com.example.todoapplication.domain.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.Todo
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

    @Query("SELECT * FROM todo WHERE id = :id ")
    fun getById(id: Long): LiveData<Todo>

//    @Insert
//    fun insertAll(user: List<User>): LongArray?

//    @Query("DELETE FROM user WHERE firstName = :firstName secondName = :secondName")
//    fun removeDuplicates(firstName:String, secondName:String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialty(specialty: Specialty): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: List<User>): LongArray?

    @Update
    fun update(user: Todo)

    @Delete
    fun delete(todo: Todo)

}