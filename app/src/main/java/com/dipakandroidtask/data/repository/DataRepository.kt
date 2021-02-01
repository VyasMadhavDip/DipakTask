package com.dipakandroidtask.data.repository

import com.dipakandroidtask.data.Failure
import com.dipakandroidtask.data.functional.Either
import com.dipakandroidtask.data.model.AlbumItem
import com.dipakandroidtask.data.model.ImageItem
import com.dipakandroidtask.data.remote.ApiService
import com.dipakandroidtask.utils.NetworkHandler
import retrofit2.Call
import javax.inject.Inject


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
class DataRepository
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: ApiService
) : DataRepositorySource {


    override fun albums(): Either<Failure, List<AlbumItem>> {
        return when (networkHandler.isConnected()) {
            true -> request(service.callAllAlbumsAPI(), { it.map { album -> album.toAlbumItem() } }, emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun images(url: String): Either<Failure, List<ImageItem>> {
        return when (networkHandler.isConnected()) {
            true -> request(service.callImagesAPI(url), { it.map { image -> image.toImageItem() } }, emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}