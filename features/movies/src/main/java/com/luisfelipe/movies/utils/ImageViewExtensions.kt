package com.luisfelipe.movies.utils

import android.widget.ImageView
import com.luisfelipe.movies.R
import com.squareup.picasso.Picasso

fun ImageView.loadBackdrop(url: String?) = Picasso
    .get()
    .load(url)
    .placeholder(R.drawable.media_backdrop_placeholder)
    .error(R.drawable.media_backdrop_placeholder)
    .into(this)