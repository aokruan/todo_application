package com.example.todoapplication.domain.service.api

import com.example.todoapplication.domain.entity.UsersBase
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface UserApi {
    @GET("65gb/static/raw/master/testTask.json")
    fun getAllUsers(): Deferred<UsersBase>
}
