package com.example.todoapplication.presentation.ui.image_details

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Image
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.image_details.ImageDetailsViewModel
import kotlinx.android.synthetic.main.fragment_image_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageDetailsFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_image_details

    private val imageDetailsViewModel by viewModel<ImageDetailsViewModel>()

    private val image by lazy {
        arguments?.get("image") as Image
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewImageLink.text = image.url
        Glide
            .with(this)
            .load(image.download_url)
            .placeholder(R.drawable.ic_camera)
            .into(imageViewImage)

    }

    override fun setListeners() {}
}
