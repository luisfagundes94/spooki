package com.luisfagundes.movies.utils.extensions

import android.content.Context
import com.luisfagundes.movies.R

fun Float.formatRating(context: Context) =
    if (this == 0.0f) context.getString(R.string.not_available)
    else this.toString()