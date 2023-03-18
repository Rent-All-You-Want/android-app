package com.pablojuice.core.presentation.view.list

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView

class ListItemDivider : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // TODO
    }

    enum class ListDividerType {
        NONE,
        SMALL,
        MEDIUM,
        LARGE,
        FULL
    }
}