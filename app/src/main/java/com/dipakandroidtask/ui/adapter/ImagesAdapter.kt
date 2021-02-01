package com.dipakandroidtask.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dipakandroidtask.R
import com.dipakandroidtask.data.model.ImageItem
import com.dipakandroidtask.databinding.ItemImageBinding
import kotlin.properties.Delegates


/**
 * Created by Dipak Vyas on 01-02-2021.
 */
class ImagesAdapter() :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    var collection: List<ImageItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item: ItemImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_image,
            parent,
            false
        )
        return ViewHolder(item)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bind(collection[position])
        }
    }

    override fun getItemCount() = collection.size

    class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: ImageItem) {
            binding.image = image
        }
    }

}