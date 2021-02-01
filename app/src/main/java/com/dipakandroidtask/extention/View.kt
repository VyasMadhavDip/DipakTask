package com.dipakandroidtask.extention

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


/**
 * Created by Dipak Vyas on 01-02-2021.
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadFromUrl(url: String) = Picasso.get().load(url).into(this)

@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl).into(view)
}
