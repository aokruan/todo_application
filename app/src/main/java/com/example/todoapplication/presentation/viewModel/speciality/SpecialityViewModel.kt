package com.example.todoapplication.presentation.viewModel.speciality

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.repository.UserRepository
import kotlinx.coroutines.launch

class SpecialityViewModel(private val userRepository: UserRepository) : ViewModel() {

    val getAllTodo = userRepository.getAllUsers
    val getAllSpecialty = userRepository.getAllSpecialty

    fun getAllUsers() {
        Log.e("Debug", "getAllUsers start")
        viewModelScope.launch {
            userRepository.refresh()
        }
    }
}