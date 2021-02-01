package com.dipakandroidtask.data.model


import com.google.gson.annotations.SerializedName

data class Albums(val albumList: ArrayList<AlbumItem>)

data class AlbumItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
) {
    fun toAlbumItem() = AlbumItem(id, title, userId)
}