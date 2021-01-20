package com.example.todoapplication.presentation.viewModel.image_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.entity.Image
import com.example.todoapplication.domain.repository.EmployeeSpecialtyRepository
import kotlinx.coroutines.launch

class ImageDetailsViewModel(private val employeeSpecialtyRepository: EmployeeSpecialtyRepository) :
    ViewModel() {

    val employeeList =
        MutableLiveData<MutableList<Image>>(mutableListOf())

    fun getAllEmployee(specialtyId: Int) {
        viewModelScope.launch {
/*            val liveData = employeeSpecialtyRepository.getEmployee(specialtyId)
            liveData.value?.let {employeeList.value = it}*/
        }
    }
}