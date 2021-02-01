package com.dipakandroidtask.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dipakandroidtask.R
import com.dipakandroidtask.data.model.AlbumItem
import com.dipakandroidtask.databinding.ItemAlbumBinding
import kotlin.properties.Delegates


/**
 * Created by Dipak Vyas on 01-02-2021.
 */
class AlbumsAdapter(private val listener: AlbumListener) :
    RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    var collection: List<AlbumItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item: ItemAlbumBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_album,
            parent,
            false
        )
        return ViewHolder(item)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(h: ViewHolder, position: Int) {
        h.apply {
            bind(collection[position])
        }
        h.itemView.setOnClickListener {
            listener.selectedAlbum(collection[position])
        }
    }

    override fun getItemCount() = collection.size

    class ViewHolder(private val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: AlbumItem) {
            binding.album = album
        }
    }

    interface AlbumListener {
        fun selectedAlbum(album: AlbumItem)
    }
}