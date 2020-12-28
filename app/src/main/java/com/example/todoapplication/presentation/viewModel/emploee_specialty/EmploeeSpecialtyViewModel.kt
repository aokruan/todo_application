package com.example.todoapplication.presentation.viewModel.emploee_specialty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.domain.entity.Emploee
import com.example.todoapplication.domain.repository.EmploeeSpecialtyRepository

class EmploeeSpecialtyViewModel(private val emploeeSpecialtyRepository: EmploeeSpecialtyRepository) :
    ViewModel() {

    private var emploeeList = MutableLiveData<MutableList<Emploee>>().apply { value = mutableListOf() }

    fun getAllEmploee(specialtyId: Int): MutableLiveData<MutableList<Emploee>> =
        emploeeSpecialtyRepository.getEmploee(specialtyId).also { emploeeList = it }
}