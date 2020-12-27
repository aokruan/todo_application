package com.example.todoapplication.presentation.ui.users_specialty

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.presentation.isVisible
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.users_specialty.UsersSpecialtyViewModel
import kotlinx.android.synthetic.main.fragment_users_specialty.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersSpecialtyFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_users_specialty

    private val usersSpecialtyViewModel by viewModel<UsersSpecialtyViewModel>()
    private var specialtyName = ""

    private val specialityAdapter = EmploeeAdapter(
        onEmployeeClick = this::routeToDetails
    )

    private fun routeToDetails(user: User) {
        findNavController().navigate(R.id.actionToEmploeeDetails, bundleOf("emploee" to user, "specialtyName" to specialtyName))
    }

    private fun changeVisibilityWarningEmptyList(user: MutableList<User>?) {
        if (user != null) {
            textViewEmptyList.isVisible = user.isEmpty()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.get("specialty") as? Specialty?)?.let { it ->
            specialtyName = it.name
            usersSpecialtyViewModel.getAllUsers(it.specialtyId)
                .observe(viewLifecycleOwner, Observer { response ->
                    response?.also {
                        changeVisibilityWarningEmptyList(response)
                        specialityAdapter.updateData(response)
                    }
                })
        }

        recyclerViewUsers.also { rv ->
            rv.layoutManager = LinearLayoutManager(activity)
            rv.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            rv.adapter = specialityAdapter
        }
    }

    override fun setListeners() {}
}
