package com.example.todoapplication.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.todoapplication.domain.dao.EmploeeDao
import com.example.todoapplication.domain.entity.Emploee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmploeeSpecialtyRepository(val emploeeDao: EmploeeDao) {

    private var emploeeList =
        MutableLiveData<MutableList<Emploee>>().apply { value = mutableListOf() }

    fun getEmploee(specialtyId: Int): MutableLiveData<MutableList<Emploee>> {
        val specialtyList = MutableLiveData(emploeeDao.getAllEmploeeBySpecialty(specialtyId))
        val emploeeListWithSpecialty: MutableList<Emploee> = mutableListOf()
        var userCounter = 0

        CoroutineScope(Dispatchers.IO).launch {
            while (userCounter < specialtyList.value?.size ?: 0) {
                specialtyList.value?.get(userCounter)?.userId?.let {
                    emploeeDao.getEmploeeById(
                        it
                    )
                }?.let { emploeeListWithSpecialty.add(it) }

                userCounter++
            }
        }

        return MutableLiveData(emploeeListWithSpecialty).also { emploeeList = it }
    }

}