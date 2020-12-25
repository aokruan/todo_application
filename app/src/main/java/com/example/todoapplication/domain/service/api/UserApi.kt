package com.example.todoapplication.domain.service.api

import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.domain.entity.UsersBase
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface UserApi {
    //@GET("65gb/static/raw/master/testTask.json")
    @GET("2fead68509d25c9ac480dbede667364b/raw/849bca743734853e9367a86ee5163d39cb160dc6/gistfile1.json")
    fun getAllUsers(): Deferred<UsersBase>
}
