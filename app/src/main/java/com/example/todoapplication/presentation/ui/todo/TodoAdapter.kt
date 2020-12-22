package com.example.todoapplication.presentation.ui.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Todo
import kotlinx.android.synthetic.main.item_todo_list.view.*

internal class TodoAdapter(var todoList: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(todo: Todo)
        fun onDeleteItemClick(todo: Todo)
    }

    private var _onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: TodoListFragment) {
        _onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo_list, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val data = todoList[i]

        viewHolder.tvTitle.text = data.title
        viewHolder.itemView.tvTitle.setOnClickListener {
            _onItemClickListener?.onItemClick(data)
        }
        viewHolder.itemView.btnDelete.setOnClickListener {
            _onItemClickListener?.onDeleteItemClick(data)
        }
    }

    fun updateData(data: List<Todo>) {
        this.todoList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = todoList.size

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    }
}