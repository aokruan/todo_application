package com.example.todoapplication.presentation.viewModel.employee_specialty

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.entity.Employee
import com.example.todoapplication.domain.repository.EmployeeSpecialtyRepository
import kotlinx.coroutines.launch

class EmployeeSpecialtyViewModel(private val employeeSpecialtyRepository: EmployeeSpecialtyRepository) :
    ViewModel() {

    val employeeList =
        MutableLiveData<MutableList<Employee>>(mutableListOf())

    fun getAllEmployee(specialtyId: Int) {
        viewModelScope.launch {
            val liveData = employeeSpecialtyRepository.getEmployee(specialtyId)
            liveData.value?.let {employeeList.value = it}
        }
    }
}