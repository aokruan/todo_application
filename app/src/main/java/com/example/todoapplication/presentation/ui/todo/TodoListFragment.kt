package com.example.todoapplication.presentation.ui.todo

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.presentation.isVisible
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.todo.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_todo_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoListFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_todo_list
    private val todoViewModel by viewModel<TodoListViewModel>()

    private val todoAdapter = TodoAdapter(
            onTodoClick = this::routeToDetails,
            onTodoClickDelete = this::deleteTodo
    )

    private fun deleteTodo(todo: Todo) {
        todoViewModel.deleteTodoAsync(todo)
        todoViewModel.getAllTodo.observe(viewLifecycleOwner, Observer {
            todoViewModel.getAllTodo.value.let {
                it?.let { response ->
                    todoAdapter.updateData(response)
                    changeVisibilityWarningEmptyList(response)
                }
            }
        })
    }

    private fun routeToDetails(todo: Todo) {
        findNavController().navigate(R.id.actionToTodoDetails, bundleOf("todo" to todo))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoViewModel.getAllTodo.observe(viewLifecycleOwner, Observer {
            todoViewModel.getAllTodo.value.let { response ->
                response?.let { it ->
                    todoAdapter.updateData(it)
                    changeVisibilityWarningEmptyList(it)
                }
            }
        })

        rvTodos.also { rv ->
            rv.layoutManager = LinearLayoutManager(activity)
            rvTodos.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            rvTodos.adapter = todoAdapter
        }
    }

    private fun changeVisibilityWarningEmptyList(todos: List<Todo>) {
        tvEmptyList.isVisible = todos.isEmpty()
    }

    override fun setListeners() {
        btnAddTodo.setOnClickListener {
            if (etTitle.text.toString().isBlank() || etDescription.text.toString().isBlank()) {
                showMessage(R.string.warning_empty_field)
            } else {
                todoViewModel.insertTodoAsync(
                        Todo(
                                null,
                                etTitle.text.toString().trim(),
                                etDescription.text.toString().trim()
                        )
                )

                todoViewModel.getAllTodo.observe(viewLifecycleOwner, Observer {
                    todoViewModel.getAllTodo.value.let { response ->
                        response?.let { it ->
                            todoAdapter.updateData(it)
                            changeVisibilityWarningEmptyList(it)
                        }
                    }
                })
                etTitle.setText("")
                etDescription.setText("")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if (etTitle.text.toString().isNotBlank() && etDescription.text.toString().isNotBlank()) {
            todoViewModel.insertTodoAsync(
                    Todo(
                            null,
                            etTitle.text.toString().trim(),
                            etDescription.text.toString().trim()
                    )
            )
        }
    }
}