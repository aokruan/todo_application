package com.example.todoapplication.domain.repository

import android.util.Log
import com.example.todoapplication.domain.dao.TodoDao
import com.example.todoapplication.domain.dao.UserDao
import com.example.todoapplication.domain.entity.UsersBase
import com.example.todoapplication.domain.service.api.UserApi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userApi: UserApi) {

    //val data = userDao.findAll()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val users = userApi.getAllUsers().await()
            Log.e("DDD", "UserRepository" + users.toString())
            //userDao.insert(users)
        }
    }
}