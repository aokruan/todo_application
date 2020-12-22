package com.example.todoapplication.presentation.viewModel.todo

import androidx.lifecycle.ViewModel
import com.example.todoapplication.domain.dao.TodoDao

class TodoDetailsViewModel(
    private val todoDao: TodoDao
) : ViewModel() {}