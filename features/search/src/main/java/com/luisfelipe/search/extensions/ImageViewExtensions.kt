package com.luisfelipe.search.extensions

import android.widget.ImageView
import com.luisfelipe.search.R
import com.squareup.picasso.Picasso

fun ImageView.loadPoster(url: String) = Picasso
    .get()
    .load(url)
    .placeholder(R.drawable.spooki_media_placeholder)
    .error(R.drawable.spooki_media_placeholder)
    .into(this)
