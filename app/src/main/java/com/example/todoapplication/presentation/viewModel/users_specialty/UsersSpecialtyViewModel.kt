package com.example.todoapplication.presentation.viewModel.users_specialty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.domain.repository.UserSpecialtyRepository

class UsersSpecialtyViewModel(private val userSpecialtyRepository: UserSpecialtyRepository) :
    ViewModel() {

    private var mList = MutableLiveData<MutableList<User>?>().apply { value = mutableListOf() }

    fun getAllUsers(specialtyId: Int): MutableLiveData<MutableList<User>?> =
        userSpecialtyRepository.getUsers(specialtyId).also { mList = it }
}