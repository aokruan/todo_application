package com.example.todoapplication.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.domain.entity.UsersBase

@Dao
interface UserDao {
    @Query("SELECT * FROM todo")
    fun getAll(): LiveData<UsersBase>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getById(id: Long): LiveData<Todo>

//    @Query("SELECT * FROM todo")
//    fun addUsers(users: List<User>)

    @Update
    fun update(user: Todo)

    @Delete
    fun delete(todo: Todo)

}