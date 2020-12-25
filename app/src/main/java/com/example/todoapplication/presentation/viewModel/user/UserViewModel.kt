package com.example.todoapplication.presentation.viewModel.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.entity.UsersBase
import com.example.todoapplication.domain.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val getAllTodo = userRepository.getAllUsers

    fun getAllUsers(){
        Log.e("Debug","getAllUsers start")
        viewModelScope.launch {
            userRepository.refresh()
        }
    }
}