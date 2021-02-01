package com.dipakandroidtask.data.remote

import com.dipakandroidtask.data.model.AlbumItem
import com.dipakandroidtask.data.model.ImageItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
interface RequestInterface {
    @GET("albums")
    fun callAllAlbumsAPI(): Call<List<AlbumItem>>

    @GET
    fun callImagesAPI(@Url url: String): Call<List<ImageItem>>
}