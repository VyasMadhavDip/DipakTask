package com.dipakandroidtask.data.repository

import com.dipakandroidtask.data.Failure
import com.dipakandroidtask.data.functional.Either
import com.dipakandroidtask.data.model.AlbumItem
import com.dipakandroidtask.data.model.ImageItem


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
interface DataRepositorySource {
    fun albums(): Either<Failure, List<AlbumItem>>
    fun images(url: String): Either<Failure, List<ImageItem>>
}