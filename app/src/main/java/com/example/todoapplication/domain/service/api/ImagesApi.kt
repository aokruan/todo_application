package com.example.todoapplication.domain.service.api

import com.example.todoapplication.domain.entity.Image
import retrofit2.http.GET

interface ImagesApi {

    @GET("list?page=2&limit=100")
    suspend fun getImages(): MutableList<Image>
}
