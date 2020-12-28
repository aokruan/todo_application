package com.example.todoapplication.presentation.viewModel.speciality

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.repository.EmploeeRepository
import kotlinx.coroutines.launch

class SpecialityViewModel(private val emploeeRepository: EmploeeRepository) : ViewModel() {
    val getAllSpecialty = emploeeRepository.getAllSpecialty

    fun getAllEmploee() {
        viewModelScope.launch {
            emploeeRepository.refresh()
        }
    }
}