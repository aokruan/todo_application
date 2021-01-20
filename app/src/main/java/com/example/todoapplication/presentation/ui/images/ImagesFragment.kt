package com.example.todoapplication.presentation.ui.images

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplication.R
import com.example.todoapplication.domain.entity.Image
import com.example.todoapplication.presentation.isVisible
import com.example.todoapplication.presentation.ui.base.BaseFragment
import com.example.todoapplication.presentation.viewModel.images.ImagesViewModel
import kotlinx.android.synthetic.main.fragment_images_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImagesFragment : BaseFragment() {
    private val imagesViewModel by viewModel<ImagesViewModel>()
    override val layoutRes: Int = R.layout.fragment_images_list

    private val imagesAdapter = ImagesAdapter(onImageClick = this::routeToDetails)

    private fun routeToDetails(image: Image) {
        findNavController().navigate(
            R.id.actionImagesFragmentToImagesDetails, bundleOf(
                "image" to image
            )
        )
    }

    private fun changeVisibilityWarningEmptyList(images: List<Image>) {
        tvEmptyList.isVisible = images.isEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        recyclerViewImages.also { rv ->
            rv.layoutManager = LinearLayoutManager(activity)
            rv.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            rv.adapter = imagesAdapter
            changeVisibilityWarningEmptyList(emptyList())
        }

        if ((activity?.let { it1 -> isOnline(it1.applicationContext) }) == false) {
            Toast.makeText(
                this.context,
                "Нет соединения с интернетом!", Toast.LENGTH_LONG
            ).show();
        } else {
            imagesViewModel.getImages()
            imagesViewModel.imagesList.observe(viewLifecycleOwner, Observer {
                changeVisibilityWarningEmptyList(it)
                imagesAdapter.updateData(it)
            })
        }
    }

    override fun setListeners() {  }

    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}