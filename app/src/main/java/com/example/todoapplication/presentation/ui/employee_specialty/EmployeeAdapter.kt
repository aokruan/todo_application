package com.example.todoapplication.presentation.ui.employee_specialty

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Employee
import com.example.todoapplication.presentation.inflate
import kotlinx.android.synthetic.main.item_employee_speciality.view.*
import kotlin.properties.Delegates
import kotlin.reflect.KFunction1

internal class EmployeeAdapter(
    private val onEmployeeClick: KFunction1<Employee, Unit>
) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    var employeeList: MutableList<Employee> by Delegates.observable(mutableListOf()) { _, _, _ ->
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return parent.inflate(R.layout.item_employee_speciality).let(::ViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employeeList[position])
    }

    fun updateData(data: MutableList<Employee>) {
        this.employeeList.clear();
        this.employeeList.addAll(data);
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = employeeList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(employee: Employee) {
            itemView.setOnClickListener {
                onEmployeeClick(employee)
            }
            with(itemView) {
                textViewEmployeeFirstName.text = employee.firstName
                textViewEmployeeSecondName.text = employee.lastName
                textViewEmployeeAge.text = "(" + employee.age + ")"
            }
        }
    }
}