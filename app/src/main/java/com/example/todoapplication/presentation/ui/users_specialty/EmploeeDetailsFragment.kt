package com.example.todoapplication.presentation.ui.users_specialty

import android.os.Bundle
import android.view.View
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_emploee_details.*

class EmploeeDetailsFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_emploee_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.get("emploee") as? User?).let { response ->
            textViewEmployeeFirstName.text = response?.firstName
            textViewEmployeeSecondName.text = response?.lastName
            textViewEmployeeBirthday.text = if (response?.birthday == "-") {
                response?.birthday
            } else {
                response?.birthday + " г."
            }
            textViewEmployeeAge.text = response?.age
            textViewEmployeeSpecialty.text = arguments?.get("specialtyName") as String
        }
    }

    override fun setListeners() {}
}
