package com.dipakandroidtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dipakandroidtask.data.model.ImageItem
import com.dipakandroidtask.data.remote.GetImages
import com.dipakandroidtask.ui.base.BaseViewModel
import javax.inject.Inject


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
class ImagesViewModel @Inject constructor(private val getImages: GetImages) : BaseViewModel() {

    private val _images: MutableLiveData<List<ImageItem>> = MutableLiveData()
    val images: LiveData<List<ImageItem>> = _images

    fun loadImages(url : String) = getImages(url){ it.fold(::handleFailure, ::handleImagesList) }

    private fun handleImagesList(images: List<ImageItem>) {
        _images.value = images
    }
}