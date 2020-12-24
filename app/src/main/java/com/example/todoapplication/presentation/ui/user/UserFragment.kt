package com.example.todoapplication.presentation.ui.user

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.todoapplication.R
import com.example.todoapplication.presentation.LoadingState
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.user.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment() {
    private val userViewModel by viewModel<UserViewModel>()
    override val layoutRes: Int = R.layout.fragment_user

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.loadingState.observe(viewLifecycleOwner, Observer {

        })
    }

    override fun setListeners() {}
}