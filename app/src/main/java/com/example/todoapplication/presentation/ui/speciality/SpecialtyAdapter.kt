package com.example.todoapplication.presentation.ui.speciality

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.presentation.inflate
import kotlinx.android.synthetic.main.item_speciality_list.view.*
import kotlin.properties.Delegates

internal class SpecialtyAdapter(
        private val onSpecialtyClick: (specialty: Specialty) -> Unit
) : RecyclerView.Adapter<SpecialtyAdapter.ViewHolder>() {

    var specialtyList: List<Specialty> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return parent.inflate(R.layout.item_speciality_list).let(::ViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(specialtyList[position])
    }

    fun updateData(data: List<Specialty>) {
        this.specialtyList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = specialtyList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(specialty: Specialty) {
            itemView.setOnClickListener {
                onSpecialtyClick(specialty)
            }
            with(itemView) {
                textViewSpecialty.text = specialty.name
            }
        }
    }
}