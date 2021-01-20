package com.example.todoapplication.presentation.ui.images

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Image
import com.example.todoapplication.presentation.inflate
import kotlinx.android.synthetic.main.item_images.view.*
import kotlin.properties.Delegates

internal class ImagesAdapter(
    private val onImageClick: (image: Image) -> Unit
) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
    var imagesList: List<Image> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return parent.inflate(R.layout.item_images).let(::ViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imagesList[position])
    }

    fun updateData(data: List<Image>) {
        this.imagesList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = imagesList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: Image) {
            itemView.setOnClickListener {
                onImageClick(image)
            }
            with(itemView) {
                textViewSpecialty.text = image.author
                Glide
                    .with(this)
                    .load(image.download_url)
                    .placeholder(R.drawable.ic_camera)
                    .into(imageViewImage)

                /*val thread = Thread {
                    try {
                        imageVievAvatar.setImageBitmap(
                            BitmapFactory.decodeStream(
                                URL(specialty.download_url)
                                    .openConnection()
                                    .getInputStream()
                            )
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                thread.start()*/
            }
        }
    }
}