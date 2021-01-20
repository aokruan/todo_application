package com.example.todoapplication.presentation.viewModel.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.entity.Image
import com.example.todoapplication.domain.repository.ImagesRepository
import kotlinx.coroutines.launch

class ImagesViewModel(private val imagesRepository: ImagesRepository) : ViewModel() {
    val imagesList =
        MutableLiveData<MutableList<Image>>(mutableListOf())

    fun getImages() {
        viewModelScope.launch {
            imagesRepository.getImages().value?.let { imagesList.value = it }
        }
    }
}