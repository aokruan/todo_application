package com.example.todoapplication.presentation.viewModel.speciality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.repository.EmployeeRepository
import kotlinx.coroutines.launch

class SpecialityViewModel(private val employeeRepository: EmployeeRepository) : ViewModel() {
    val getAllSpecialty = employeeRepository.getAllSpecialty

    fun getAllEmployee() {
        viewModelScope.launch {
            employeeRepository.refresh()
        }
    }
}