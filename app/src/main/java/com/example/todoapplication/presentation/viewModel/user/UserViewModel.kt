package com.example.todoapplication.presentation.viewModel.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.entity.UsersBase
import com.example.todoapplication.domain.repository.UserRepository
import com.example.todoapplication.presentation.LoadingState
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var _loadingState = MutableLiveData<UsersBase>()
    val loadingState: LiveData<UsersBase>
        get() = _loadingState

    //val getAllTodo = userRepository.getAllTodo

    //val data = userRepository.data

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                loadingState.value
                userRepository.refresh()

            } catch (e: Exception) {
            }
        }
    }
}