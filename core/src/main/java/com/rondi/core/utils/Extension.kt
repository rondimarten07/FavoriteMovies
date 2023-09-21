package com.rondi.core.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rondi.core.BuildConfig


fun ImageView.showImageInto(context: Context, url: String?){
    Glide.with(context)
        .load("${BuildConfig.BASE_IMAGE_URL}$url")
        .centerCrop()
        .into(this)
}

fun ImageView.loadImage(url: String, requestOptions: RequestOptions = RequestOptions()) {
    Glide.with(this.context)
        .load(url)
        .apply(requestOptions)
        .centerCrop()
        .into(this)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}