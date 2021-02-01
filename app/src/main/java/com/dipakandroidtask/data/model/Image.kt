package com.dipakandroidtask.data.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso


data class Images(val imageList: ArrayList<ImageItem>)

data class ImageItem(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
) {
    fun toImageItem() = ImageItem(albumId, id, thumbnailUrl, title, url)
}