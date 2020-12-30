package com.example.todoapplication.presentation.ui.employee_specialty

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Employee
import com.example.todoapplication.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_employee_details.*

class EmployeeDetailsFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_employee_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.get("employee") as? Employee?).let { response ->
            textViewEmployeeFirstName.text = response?.firstName
            textViewEmployeeSecondName.text = response?.lastName
            textViewEmployeeBirthday.text = if (response?.birthday == "-") {
                response.birthday
            } else {
                response?.birthday + " г."
            }
            textViewEmployeeAge.text = response?.age
            textViewEmployeeSpecialty.text = arguments?.get("specialtyName") as String

            try {
                activity?.let {
                    Glide.with(it.applicationContext)
                        .load(response?.avatrUrl)
                        .error(R.drawable.ic_camera)
                        .into(imageVievAvatar)
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    override fun setListeners() {}
}
