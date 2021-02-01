package com.dipakandroidtask.data.remote

import com.dipakandroidtask.data.interactor.UseCase
import com.dipakandroidtask.data.model.AlbumItem
import com.dipakandroidtask.data.repository.DataRepository
import javax.inject.Inject


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
class GetAlbums @Inject constructor(private val dataRepository: DataRepository) : UseCase<List<AlbumItem>, UseCase.None>() {

    override suspend fun run(params: None) = dataRepository.albums()
}