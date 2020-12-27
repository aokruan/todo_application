package com.example.todoapplication.presentation.ui.speciality

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.presentation.isVisible
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.speciality.SpecialityViewModel
import kotlinx.android.synthetic.main.fragment_specialty_list.*
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat
import org.koin.androidx.viewmodel.ext.android.viewModel


class SpecialityFragment : BaseFragment() {

    private val specialityViewModel by viewModel<SpecialityViewModel>()
    override val layoutRes: Int = R.layout.fragment_specialty_list

    private val specialityAdapter = SpecialtyAdapter(
        onSpecialtyClick = this::routeToDetails
    )

    private fun routeToDetails(specialty: Specialty) {
        Log.e("Debug", "ID SPECIALTY: >>>> " + specialty.specialtyId)
        findNavController().navigate(
            R.id.action_specialityFragment_to_actionToUsersBySpecialty, bundleOf(
                "specialty" to specialty
            )
        )
    }

    private fun changeVisibilityWarningEmptyList(specialty: List<Specialty>) {
        tvEmptyList.isVisible = specialty.isEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        specialityViewModel.getAllSpecialty.observe(viewLifecycleOwner, Observer {
            specialityViewModel.getAllSpecialty.value.let { response ->
                response?.let { it ->
                    specialityAdapter.updateData(it)
                    changeVisibilityWarningEmptyList(it)
                }
            }
        })

        recyclerViewSpecialty.also { rv ->
            rv.layoutManager = LinearLayoutManager(activity)
            rv.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            rv.adapter = specialityAdapter
        }

    }

    override fun setListeners() {

        buttonSaveToDb.setOnClickListener {
            /*Дергает методы во ViewModel
            *
            * В связи с реализацией в репозитории
            * данные сразу заполняются в БД
            * */
            specialityViewModel.getAllUsers()
        }

        buttonLoadFromDb.setOnClickListener {

            /*//По клику не загружает, т.к. подписывается на изменения
            userViewModel.getAllTodo.observe(viewLifecycleOwner, Observer {
                Log.e("Debug", "getAllTodo: " + it.toString())
            })*/
//            specialityViewModel.getAllSpecialty.observe(viewLifecycleOwner, Observer {
//                specialityAdapter.updateData(it)
//                changeVisibilityWarningEmptyList(it)
//            })
        }
    }
}