package com.example.todoapplication.presentation.ui.todo

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.presentation.inflate
import kotlinx.android.synthetic.main.fragment_todo_details.view.*
import kotlinx.android.synthetic.main.fragment_todo_list.view.*
import kotlinx.android.synthetic.main.item_todo_list.view.*
import kotlinx.android.synthetic.main.item_todo_list.view.tvDescription
import kotlinx.android.synthetic.main.item_todo_list.view.tvTitle
import kotlin.properties.Delegates

internal class TodoAdapter(
        private val onTodoClick: (todo: Todo) -> Unit,
        private val onTodoClickDelete: (todo: Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    var todoList: List<Todo> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return parent.inflate(R.layout.item_todo_list).let(::ViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    fun updateData(data: List<Todo>) {
        this.todoList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = todoList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: Todo) {
            itemView.btnDelete.setOnClickListener {
                onTodoClickDelete(todo)
            }
            itemView.setOnClickListener {
                onTodoClick(todo)
            }
            with(itemView) {
                tvTitle.text = todo.title
                tvDescription.text = todo.description
            }
        }
    }
}