package com.luisfagundes.commons_ui

import android.widget.ImageView
import com.luisfagundes.base.R
import com.squareup.picasso.Picasso

fun ImageView.loadLogo(url: String?) = Picasso
    .get()
    .load(url)
    .placeholder(R.color.mine_shaft)
    .error(R.color.mine_shaft)
    .into(this)