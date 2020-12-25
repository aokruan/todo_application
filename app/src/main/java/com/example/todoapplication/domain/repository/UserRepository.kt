package com.example.todoapplication.domain.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todoapplication.domain.dao.UserDao
import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.domain.service.api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.anko.collections.forEachByIndex

class UserRepository(private val userApi: UserApi, val userDao: UserDao) {

    val getAllUsers = userDao.getAll()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val users = userApi.getAllUsers().await()
            Log.e("Debug", users.response.toString())

            //Обрабатываем битые поляв
            val data:MutableList<User> = mutableListOf()
            var i = 0
            while (i<users.response.size){
                data.add(users.response[i].format())
                i++
            }

            //Log.e("Debug", data.toString())

            userDao.insertAll(data)
        }
    }
}