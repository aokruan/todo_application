package com.example.todoapplication.presentation.ui.users_specialty

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.presentation.inflate
import kotlinx.android.synthetic.main.fragment_emploee_details.view.*
import kotlin.properties.Delegates
import kotlin.reflect.KFunction1

internal class EmploeeAdapter(
    private val onEmployeeClick: KFunction1<User, Unit>
) : RecyclerView.Adapter<EmploeeAdapter.ViewHolder>() {

    var emploeeList: List<User> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return parent.inflate(R.layout.item_emploee_speciality).let(::ViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(emploeeList[position])
    }

    fun updateData(data: List<User>) {
        this.emploeeList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = emploeeList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.setOnClickListener {
                onEmployeeClick(user)
            }
            with(itemView) {
                textViewEmployeeFirstName.text = user.firstName
                textViewEmployeeSecondName.text = user.lastName
                textViewEmployeeAge.text = "(" + user.age + ")"
            }
        }
    }
}