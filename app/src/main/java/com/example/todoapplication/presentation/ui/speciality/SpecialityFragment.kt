package com.example.todoapplication.presentation.ui.speciality

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpecialityFragment : BaseFragment() {
    private val specialityViewModel by viewModel<SpecialityViewModel>()
    override val layoutRes: Int = R.layout.fragment_specialty_list

    private val specialityAdapter = SpecialtyAdapter(onSpecialtyClick = this::routeToDetails)

    private fun routeToDetails(specialty: Specialty) {
        findNavController().navigate(
            R.id.actionSpecialityFragmentToEmployeeSpecialty, bundleOf(
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
        buttonLoadData.setOnClickListener {
            if ((activity?.let { it1 -> isOnline(it1.applicationContext) })  == false) {
                Toast.makeText(
                    this.context,
                    "Нет соединения с интернетом!", Toast.LENGTH_LONG
                ).show();
            } else {
                // Активируем запрос в сеть
                specialityViewModel.getAllEmployee()
            }
        }
    }

    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}