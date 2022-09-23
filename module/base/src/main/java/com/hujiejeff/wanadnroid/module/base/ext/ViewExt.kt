package com.hujiejeff.wanadnroid.module.base.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hujiejeff.wanadnroid.module.base.R

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
//        .placeholder(R.m)
//        .error(R.drawable.default_cover)
        .into(this)
}