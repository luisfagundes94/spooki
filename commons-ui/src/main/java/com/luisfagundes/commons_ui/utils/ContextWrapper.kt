package com.luisfagundes.commons_ui.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.luisfagundes.commons_ui.R

class ContextWrapper(private val context: Context) {
    fun getDefaultPlaceholder() = ContextCompat.getColor(context, R.color.mine_shaft)
    fun getErrorPlaceholder() = ContextCompat.getDrawable(context, R.drawable.spooki_media_placeholder)
}