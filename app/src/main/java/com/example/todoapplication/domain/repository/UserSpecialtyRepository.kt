package com.example.todoapplication.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.todoapplication.domain.dao.UserDao
import com.example.todoapplication.domain.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserSpecialtyRepository(val userDao: UserDao) {

    private var mCountryList =
        MutableLiveData<MutableList<User>?>().apply { value = mutableListOf() }

    fun getUsers(specialtyId: Int): MutableLiveData<MutableList<User>?> {
        val mSpecialty = MutableLiveData(userDao.getAllUsersBySpecialty(specialtyId))
        val users: MutableList<User> = mutableListOf()
        var userCounter = 0

        CoroutineScope(Dispatchers.IO).launch {
            while (userCounter < mSpecialty.value?.size ?: 0) {
                users.add(userDao.getUserById(mSpecialty.value!![userCounter].userId))

                userCounter++
            }
        }
        return MutableLiveData(users).also { mCountryList = it }
    }

}