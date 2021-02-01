package com.dipakandroidtask.data.remote

import com.dipakandroidtask.data.interactor.UseCase
import com.dipakandroidtask.data.model.ImageItem
import com.dipakandroidtask.data.repository.DataRepository
import javax.inject.Inject

/**
 * Created by Dipak Vyas on 31-01-2021.
 */
class GetImages
@Inject constructor(private val dataRepository: DataRepository) : UseCase<List<ImageItem>, String>() {
    override suspend fun run(params: kotlin.String) = dataRepository.images(params)
}