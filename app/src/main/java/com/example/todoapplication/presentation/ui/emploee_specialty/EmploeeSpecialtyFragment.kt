package com.example.todoapplication.presentation.ui.emploee_specialty

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Emploee
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.presentation.isVisible
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.emploee_specialty.EmploeeSpecialtyViewModel
import kotlinx.android.synthetic.main.fragment_emploee_specialty_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmploeeSpecialtyFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_emploee_specialty_list

    private val emploeeSpecialtyViewModel by viewModel<EmploeeSpecialtyViewModel>()
    private var specialtyName = ""

    private val specialityAdapter = EmploeeAdapter(onEmployeeClick = this::routeToDetails)

    private fun routeToDetails(emploee: Emploee) {
        findNavController().navigate(
            R.id.actionToEmploeeDetails,
            bundleOf("emploee" to emploee, "specialtyName" to specialtyName)
        )
    }

    private fun changeVisibilityWarningEmptyList(emploeeList: MutableList<Emploee>) {
            textViewEmptyList.isVisible = emploeeList.size != 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.get("specialty") as Specialty).let { it ->
            specialtyName = it.name
            emploeeSpecialtyViewModel.getAllEmploee(it.specialtyId)
                .observe(viewLifecycleOwner, Observer { response ->

                    response?.also {
                       //changeVisibilityWarningEmptyList(response)
                        specialityAdapter.updateData(response)

                        Log.e("Debug", ">>>>>" + response)
                    }
                })
        }

        recyclerViewEmploee.also { rv ->
            rv.layoutManager = LinearLayoutManager(activity)
            rv.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            rv.adapter = specialityAdapter
        }
    }

    override fun setListeners() {}
}
