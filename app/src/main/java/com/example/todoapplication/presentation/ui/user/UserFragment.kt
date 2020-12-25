package com.example.todoapplication.presentation.ui.user

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.todoapplication.R
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.user.UserViewModel
import kotlinx.android.synthetic.main.fragment_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment() {
    private val userViewModel by viewModel<UserViewModel>()
    override val layoutRes: Int = R.layout.fragment_user

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("Debug", "Fragment start")
        showMessage(R.string.app_name)


    }

    override fun setListeners() {

        buttonSaveToDb.setOnClickListener {
            /*Дергает методы во ViewModel
            *
            * В связи с реализацией в репозитории
            * данные сразу заполняются в БД
            * */
            userViewModel.getAllUsers()
        }

        buttonLoadFromDb.setOnClickListener {
            //По клику не загружает, т.к. подписывается на изменения
            userViewModel.getAllTodo.observe(viewLifecycleOwner, Observer {
                Log.e("Debug", "getAllTodo: " + it.toString())
            })
        }
    }
}