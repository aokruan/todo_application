package com.example.todoapplication.presentation.ui.employee_specialty

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Employee
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.presentation.isVisible
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.employee_specialty.EmployeeSpecialtyViewModel
import kotlinx.android.synthetic.main.fragment_employee_specialty_list.*
import kotlinx.android.synthetic.main.fragment_specialty_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class EmployeeSpecialtyFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_employee_specialty_list

    private val employeeSpecialtyViewModel by viewModel<EmployeeSpecialtyViewModel>()

    private val specialty by lazy {
        arguments?.get("specialty") as Specialty
    }

    private val specialityAdapter = EmployeeAdapter(onEmployeeClick = this::routeToDetails)

    private fun routeToDetails(employee: Employee) {
        findNavController().navigate(
            R.id.employeeDetailsFragment,
            bundleOf("employee" to employee, "specialtyName" to specialty.name)
        )
    }

    private fun changeVisibilityWarningEmptyList(employeeList: MutableList<Employee>) {
        textViewEmptyList.isVisible = employeeList.isEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewEmployee.also { rv ->
            rv.layoutManager = LinearLayoutManager(activity)
            rv.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            rv.adapter = specialityAdapter
        }

        employeeSpecialtyViewModel.getAllEmployee(specialty.specialtyId)
        employeeSpecialtyViewModel.employeeList.observe(viewLifecycleOwner, Observer {
            changeVisibilityWarningEmptyList(it)
            specialityAdapter.updateData(it)
        })
    }

    override fun setListeners() {}
}
