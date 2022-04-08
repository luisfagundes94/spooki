package com.luisfagundes.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadLogo(url: String?, placeholder: Int, error: Int) = Picasso
    .get()
    .load(url)
    .placeholder(placeholder)
    .error(error)
    .into(this)