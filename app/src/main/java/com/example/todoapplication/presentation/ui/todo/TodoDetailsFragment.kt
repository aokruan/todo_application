package com.example.todoapplication.presentation.ui.todo

import android.os.Bundle
import android.view.View
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.todo.TodoDetailsViewModel
import kotlinx.android.synthetic.main.fragment_todo_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoDetailsFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_todo_details
    private val todoDetailsViewModel by viewModel<TodoDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todo = arguments?.get("todo") as? Todo

        todo?.let { it ->
            tvTitle.text = it.title
            tvDescription.text = it.description
        }
    }

    override fun setModelBindings() {}

    override fun setListeners() {}
}
