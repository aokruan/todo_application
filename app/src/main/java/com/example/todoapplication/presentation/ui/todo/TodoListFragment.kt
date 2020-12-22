package com.example.todoapplication.presentation.ui.todo

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.presentation.isVisible
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.todo.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_todo_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoListFragment : BaseFragment(), TodoAdapter.OnItemClickListener {

    override val layoutRes: Int = R.layout.fragment_todo_list
    private val todoViewModel by viewModel<TodoListViewModel>()

    private var todoList = listOf<Todo>()
    private var todoAdapter = TodoAdapter(todoList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoViewModel.getAllTodo.observe(this.viewLifecycleOwner, Observer {
            this.todoViewModel.getAllTodo.value.let { response ->
                response?.let { it ->
                    todoAdapter.updateData(it)
                    setEmptyListStatus(it)
                }
            }
        })

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rvTodos.also { rv ->
            rv.layoutManager = LinearLayoutManager(activity)
            rvTodos.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            rvTodos.adapter = todoAdapter
            todoAdapter.setOnItemClickListener(this)
        }
    }

    private fun setEmptyListStatus(todos: List<Todo>) {
        tvEmptyList.isVisible = todos.isEmpty()
    }

    override fun setModelBindings() {}

    override fun setListeners() {
        btnAddTodo.setOnClickListener {
            if (etTitle.text.toString().isBlank() || etDescription.text.toString().isBlank()) {
                showMessage(R.string.warning_empty_field)
            } else {
                todoViewModel.insertTodoAsync(
                    Todo(
                        null,
                        etTitle.text.toString(),
                        etDescription.text.toString()
                    )
                )

                todoViewModel.getAllTodo.observe(this.viewLifecycleOwner, Observer {
                    this.todoViewModel.getAllTodo.value.let { response ->
                        response?.let { it ->
                            todoAdapter.updateData(it)
                            setEmptyListStatus(it)
                        }
                    }
                })
                etTitle.setText("")
                etDescription.setText("")
            }
        }
    }

    private fun routeToDetails(todo: Todo) {
        findNavController().navigate(
            R.id.actionToTodoDetails,
            bundleOf("todo" to todo)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if (etTitle.text.toString().isNotBlank() && etDescription.text.toString().isNotBlank()) {
            todoViewModel.insertTodoAsync(
                Todo(
                    null,
                    etTitle.text.toString(),
                    etDescription.text.toString()
                )
            )
        }
    }

    override fun onItemClick(todo: Todo) {
        routeToDetails(todo)
    }

    override fun onDeleteItemClick(todo: Todo) {
        todo.let { it ->
            todoViewModel.deleteTodoAsync(it)
            todoViewModel.getAllTodo.observe(this.viewLifecycleOwner, Observer {
                this.todoViewModel.getAllTodo.value.let {
                    it?.let { response ->
                        todoAdapter.updateData(response)
                        setEmptyListStatus(response)
                    }
                }
            })
        }
    }
}
