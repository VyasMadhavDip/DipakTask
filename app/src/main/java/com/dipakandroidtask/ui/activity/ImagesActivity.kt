package com.dipakandroidtask.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dipakandroidtask.R
import com.dipakandroidtask.dagger.ViewModelFactory
import com.dipakandroidtask.data.Failure
import com.dipakandroidtask.data.model.ImageItem
import com.dipakandroidtask.databinding.ActivityImagesBinding
import com.dipakandroidtask.extention.*
import com.dipakandroidtask.ui.adapter.ImagesAdapter
import com.dipakandroidtask.utils.Constants
import com.dipakandroidtask.viewmodel.ImagesViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by Dipak Vyas on 31-01-2021.
 */
class ImagesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ImagesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityImagesBinding
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var albumID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_images)
        viewModel = viewModel(viewModelFactory)
        init()
    }

    private fun init() {
        supportActionBar?.title = getString(R.string.title_activity_images)
        albumID = intent.getIntExtra("albumID",-1).toString()
        setAdapter()
        observeVM()
        loadImages()
    }

    private fun setAdapter() {
        imagesAdapter = ImagesAdapter()
        binding.rvImagesList.adapter = imagesAdapter
    }

    private fun observeVM() {
        viewModel.apply {
            observe(images, ::renderImagesList)
            failure(failure, ::handleFailure)
        }
    }

    private fun loadImages() {
        binding.apply {
            rvImagesList.visible()
            progressBar.visible()
            tvNoData.gone()
        }
        viewModel.loadImages(Constants.BASE_URL + "albums/" + albumID + "/photos")
    }

    private fun renderImagesList(albums: List<ImageItem>?) {
        binding.progressBar.gone()
        imagesAdapter.collection = albums.orEmpty()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(getString(R.string.failure_network_connection))
            is Failure.ServerError -> renderFailure(getString(R.string.failure_server_error))
            is Failure.ListNotAvailable -> renderFailure(getString(R.string.failure_albums_list_unavailable))
            else -> {
            }
        }
    }

    private fun renderFailure(message: String) {
        binding.apply {
            rvImagesList.gone()
            progressBar.gone()
            tvNoData.visible()
        }
        showToast(message)
    }
}