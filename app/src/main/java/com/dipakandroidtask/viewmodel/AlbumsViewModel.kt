package com.dipakandroidtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dipakandroidtask.data.interactor.UseCase
import com.dipakandroidtask.data.model.AlbumItem
import com.dipakandroidtask.data.remote.GetAlbums
import com.dipakandroidtask.ui.base.BaseViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
class AlbumsViewModel
@Inject constructor(private val getAlbums: GetAlbums) : BaseViewModel() {

    private val _albums: MutableLiveData<List<AlbumItem>> = MutableLiveData()
    val albums: LiveData<List<AlbumItem>> = _albums
    private var filterData = ArrayList<AlbumItem>()
    private var data = ArrayList<AlbumItem>()

    fun loadMovies() = getAlbums(UseCase.None()) { it.fold(::handleFailure, ::handleAlbumList) }

    private fun handleAlbumList(albums: List<AlbumItem>) {
        _albums.value = albums
        data = albums as ArrayList<AlbumItem>
    }

    fun onSearchClick(albumName: String) {
        filterData = if (albumName.isEmpty()) {
            data
        } else {
            val resultList = ArrayList<AlbumItem>()
            for (row in data) {
                if (row.title.toLowerCase(Locale.ROOT)
                        .contains(albumName.toLowerCase(Locale.ROOT))
                ) {
                    resultList.add(row)
                }
            }
            resultList
        }
        _albums.value = filterData
    }
}