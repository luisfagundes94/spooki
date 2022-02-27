package com.luisfelipe.extensions

import android.graphics.Outline
import android.os.Build
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.luisfelipe.base.R
import com.squareup.picasso.Picasso

fun ImageView.load(url: String) = Picasso
    .get()
    .load(url)
    .into(this)

fun ImageView.loadPoster(url: String?) = Picasso
    .get()
    .load(url)
    .placeholder(R.drawable.spooki_media_placeholder)
    .error(R.drawable.spooki_media_placeholder)
    .into(this)


fun ImageView.setCustomLowerCorners(curveRadius: Float = 40F) {
    this.outlineProvider = object : ViewOutlineProvider() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setRoundRect(0, -curveRadius.toInt(), view!!.width, view.height, curveRadius)
        }
    }
    this.clipToOutline = true
}
