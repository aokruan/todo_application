package com.example.todoapplication.presentation.ui.todo

import android.os.Bundle
import android.view.View
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_todo_details.*

class TodoDetailsFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_todo_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.get("todo") as? Todo?).let { it ->
            tvTitle.text = it?.title
            tvDescription.text = it?.description
        }
    }

    override fun setListeners() {}
}
