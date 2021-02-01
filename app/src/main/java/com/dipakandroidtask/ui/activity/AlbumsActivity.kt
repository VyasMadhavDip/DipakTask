package com.dipakandroidtask.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dipakandroidtask.R
import com.dipakandroidtask.dagger.ViewModelFactory
import com.dipakandroidtask.data.Failure
import com.dipakandroidtask.data.model.AlbumItem
import com.dipakandroidtask.databinding.ActivityAlbumsBinding
import com.dipakandroidtask.extention.*
import com.dipakandroidtask.ui.adapter.AlbumsAdapter
import com.dipakandroidtask.viewmodel.AlbumsViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by Dipak Vyas on 31-01-2021.
 */
class AlbumsActivity : AppCompatActivity(), AlbumsAdapter.AlbumListener {

    @Inject
    lateinit var viewModel: AlbumsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityAlbumsBinding
    private lateinit var albumAdapter: AlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_albums)
        viewModel = viewModel(viewModelFactory)
        binding.viewModel = viewModel
        init()
    }

    private fun init() {
        supportActionBar?.title = getString(R.string.title_activity_albums)
        setAdapter()
        observeVM()
        loadAlbums()
        search()
    }

    private fun setAdapter() {
        albumAdapter = AlbumsAdapter(this)
        binding.rvAlbumList.adapter = albumAdapter
    }

    private fun observeVM() {
        viewModel.apply {
            observe(albums, ::renderAlbumsList)
            failure(failure, ::handleFailure)
        }
    }

    private fun loadAlbums() {
        binding.apply {
            rvAlbumList.visible()
            progressBar.visible()
            tvNoData.gone()
        }
        viewModel.loadMovies()
    }

    private fun renderAlbumsList(albums: List<AlbumItem>?) {
        binding.progressBar.gone()
        albumAdapter.collection = albums.orEmpty()
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
            rvAlbumList.gone()
            progressBar.gone()
            tvNoData.visible()
        }
        showToast(message)
    }

    override fun selectedAlbum(album: AlbumItem) {
        Intent(this, ImagesActivity::class.java).apply {
            putExtra("albumID", album.id)
            startActivity(this)
        }
    }

    private fun search() {
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().isNotEmpty()) {
                    viewModel.onSearchClick(editable.toString())
                }
            }
        })
    }
}