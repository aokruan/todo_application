package com.example.todoapplication.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.domain.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getById(id: Long): LiveData<Todo>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    @JvmSuppressWildcards
//    @Relation(parentColumn = "id", entityColumn = "employ_id")
    //fun insertAll(user: List<User>)

//    @Insert
//    fun insertAll(user: List<User>): LongArray?

//    @Query("DELETE FROM user WHERE firstName = :firstName secondName = :secondName")
//    fun removeDuplicates(firstName:String, secondName:String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: List<User>): LongArray?

    @Update
    fun update(user: Todo)

    @Delete
    fun delete(todo: Todo)

}