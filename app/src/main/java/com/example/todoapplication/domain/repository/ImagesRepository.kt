package com.example.todoapplication.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.todoapplication.domain.entity.Image
import com.example.todoapplication.domain.service.api.ImagesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepository(private val imagesApi: ImagesApi) {
    private var imagesList =
        MutableLiveData<MutableList<Image>>().apply { value = mutableListOf() }

    suspend fun getImages(): MutableLiveData<MutableList<Image>> =
        withContext(Dispatchers.IO) {
            MutableLiveData(imagesApi.getImages()).also { imagesList = it }
        }

}