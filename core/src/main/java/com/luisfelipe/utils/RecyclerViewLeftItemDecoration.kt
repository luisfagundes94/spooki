package com.luisfelipe.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class RecyclerViewLeftItemMargin(
    private val margin: Int
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)

        if (position == FIRST_ITEM_POSITION) {
            outRect.left = margin
        }
    }

    private companion object {
        const val FIRST_ITEM_POSITION = 0
    }
}