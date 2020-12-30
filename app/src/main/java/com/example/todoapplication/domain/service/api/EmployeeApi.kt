package com.example.todoapplication.domain.service.api

import com.example.todoapplication.domain.entity.Data
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface EmployeeApi {
    @GET("65gb/static/raw/master/testTask.json")
    fun getAllEmployee(): Deferred<Data>
}