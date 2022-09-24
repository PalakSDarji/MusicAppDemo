package com.palak.applemusicappdemo.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlin.collections.ArrayList

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("url")
fun bindImageUrls(view: ImageView, urls: ArrayList<String>) {
    urls.firstOrNull()?.let { url ->
        Glide.with(view.context).load(url).into(view)
    }
}