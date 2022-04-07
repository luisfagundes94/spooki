package com.luisfagundes.movies.utils

import android.widget.ImageView
import com.luisfagundes.movies.R
import com.squareup.picasso.Picasso

fun ImageView.loadBackdrop(url: String?) = Picasso
    .get()
    .load(url)
    .placeholder(R.drawable.media_backdrop_placeholder)
    .error(R.drawable.media_backdrop_placeholder)
    .into(this)

fun ImageView.loadThumbnail(url: String?) = Picasso
    .get()
    .load(url)
    .placeholder(R.drawable.media_backdrop_placeholder)
    .error(R.drawable.media_backdrop_placeholder)
    .into(this)