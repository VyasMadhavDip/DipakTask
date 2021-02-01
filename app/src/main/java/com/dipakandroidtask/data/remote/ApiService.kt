package com.dipakandroidtask.data.remote

import com.dipakandroidtask.data.model.AlbumItem
import com.dipakandroidtask.data.model.ImageItem
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
@Singleton
class ApiService
@Inject constructor(retrofit: Retrofit) : RequestInterface {
    private val apis by lazy { retrofit.create(RequestInterface::class.java) }
    override fun callAllAlbumsAPI(): Call<List<AlbumItem>> = apis.callAllAlbumsAPI()
    override fun callImagesAPI(url: String): Call<List<ImageItem>> = apis.callImagesAPI(url)
}