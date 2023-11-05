package com.pablojuice.core.presentation.view.list

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemSwipeToRemoveHelper(
    val onItemRemovedCallback: (Int) -> Unit,
    val swipeDirection: SwipeDirection = SwipeDirection.HORIZONTAL
) : ItemTouchHelper(object : SimpleCallback(0, swipeDirection.direction) {
    override fun onMove(
        recyclerView: RecyclerView,
        previous: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (viewHolder.canBeRemoved()) super.getSwipeDirs(recyclerView, viewHolder) else 0
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) =
        onItemRemovedCallback(viewHolder.adapterPosition)

    override fun isItemViewSwipeEnabled(): Boolean = true

}) {

    fun interface RemovableBySwipe {
        fun canBeRemoved(): Boolean
    }

    enum class SwipeDirection(val direction: Int) {
        VERTICAL(UP or DOWN), HORIZONTAL(LEFT or RIGHT)
    }
}

private fun RecyclerView.ViewHolder.canBeRemoved() =
    this is ItemSwipeToRemoveHelper.RemovableBySwipe && this.canBeRemoved()