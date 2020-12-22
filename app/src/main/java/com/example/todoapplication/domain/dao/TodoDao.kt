package com.example.todoapplication.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapplication.domain.entity.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getById(id: Long): LiveData<Todo>

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)

}