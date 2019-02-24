package com.kotlinacc.kimyounghoon.kotlinacc.bindingadapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.kotlinacc.kimyounghoon.kotlinacc.GlideApp

@BindingAdapter("app:userImageUrl")
fun loadUserImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView)
            .load(url)
            .override(50, 50)
            .circleCrop()
            .into(imageView)
}

@BindingAdapter("app:photoImageUrl")
fun loadPhotoImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView)
            .load(url)
            .override(200, 300)
            .into(imageView)
}